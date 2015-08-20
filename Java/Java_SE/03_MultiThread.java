//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : Downloader.java
//  Date      : 9/26/14
//  Objective : This program demonstrates how to download a file
//              using a URL that user input as an argument and
//              displays how much byte has been downloaded 
//              during the download and also displays download
//              indicator which lets user know that the file is
//              now being downloaded. 
//  Java version : 1.8.0_20
//  Library   : CS211S_Lib.java version 1.2
//**************************************************************

import static library.CS211S_Lib.sleep;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.URL;

public class Downloader
{
    private static String strFileName;
    private static int totalDownloadedByte = 0;
    private static boolean isDownloaded = false;
    private static int paramForIndicator = 0;
    private static final char[] INDICATOR = new char[] {'|', '/', '-', '\\'};
    private static int tempByte;
    private static char charIndicator;

    // ------------ downloadFile() -----------------------------
    // Download a file using the URL that user input.
    private static void downloadFile(String urlString)
    {
        try
        {
            int size;
            byte[] byteBuf = new byte[4096];

            // Instantiate URL class.
            URL url = new URL(urlString);

            // Set the file name to be downloaded.
            strFileName = url.getPath();
            if (strFileName.equals(""))
            {
                strFileName = "index.html";
            }
            else
            {
                strFileName = strFileName
                        .substring(strFileName.lastIndexOf("/") + 1);
            }

            // Set the buffer for input and output.
            BufferedInputStream bufIn = new BufferedInputStream(url.openStream());
            BufferedOutputStream bufOut = new BufferedOutputStream(
                    new FileOutputStream(strFileName));

            // Write it out.
            while ((size = bufIn.read(byteBuf)) != -1)
            {
                bufOut.write(byteBuf, 0, size);
                totalDownloadedByte += size;
            }

            isDownloaded = true;

            // Close.
            bufIn.close();
            bufOut.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // ------------ locate() -----------------------------------
    // Locate the cursor on the selected row and column.
    public static void locate(int row, int col)
    {
        for (int i = 0; i < row; i++)
            System.out.println();
        for (int i = 0; i < col; i++)
            System.out.print(" ");
    }

    // ------------ fan() --------------------------------------
    // Indicate that downloading is going on.
    public static void fan()
    {
        charIndicator = INDICATOR[paramForIndicator % 4];

        // For changing to the next charIndicator.
        paramForIndicator++;
    }

    // ------------ changeByteOrIndicator() --------------------
    // Create the line to be displayed and display the line.
    public static synchronized void changeByteOrIndicator(int nByte,
            char charIndicator)
    {
        System.out.print("\r");
        System.out
                .printf("Downloading " + strFileName + " ... %8d" + " Byte", nByte);
        System.out.print("  ");
        System.out.print(charIndicator);
        System.out.print("                ");
    }

    // ------------ main() -------------------------------------
    // Program starts with this method.
    public static void main(String[] args)
    {
        // Thread-0 downloads a file.
        Thread thread0 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                if (args.length == 0)
                {
                    System.out.println("");
                    System.out.println("Usage: ");
                    System.out.println("  java HttpGet [URL]");
                    System.out.println("");
                    System.out.println("e.g. : ");
                    System.out.println("  C:\\> java HttpGet "
                            + "http://www.yahoo.co.jp");
                    System.out.println("");
                    System.out.println("  C:\\> java HttpGet "
                            + "http://www.dennou-sedai.jp" + "/JavaSource"
                            + "/HankakuToZenkaku.html");
                    System.out.println("");
                }
                else
                {
                    downloadFile(args[0]);
                }
            }
        });
        thread0.start();

        // Create some spaces on the console.
        locate(2, 0);

        // Thread-1 displays how many bytes has been downloaded.
        Thread thread1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (!isDownloaded)
                {
                    // Display the current downloded byte and
                    // the current charIndicator.
                    changeByteOrIndicator(totalDownloadedByte, charIndicator);

                    // For thread-2 not to change the downloaded
                    // byte when it changes the charIndicator.
                    tempByte = totalDownloadedByte;

                    sleep(500);
                }

                System.out.print("\r");
                System.out.print("Download is done." + "                 "
                        + "               \n");
            }
        });
        thread1.start();

        // Thread-2 displays an icon which lets user know that
        // a file is now being downloaded.
        Thread thread2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (!isDownloaded)
                {
                    // Change the charIndicator to the next.
                    fan();

                    // Display the charIndicator and
                    // not change the downloaded byte.
                    changeByteOrIndicator(tempByte, charIndicator);

                    sleep(50);
                }
            }
        });
        thread2.start();
    } // end of main().
}

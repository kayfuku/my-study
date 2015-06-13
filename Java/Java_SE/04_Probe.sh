#!/bin/bash
#**************************************************************
#  Author    : Fukutani, Kei
#  File Name : Probe
#  Date      : 10/10/14
#  Objective : This wrapper is supposed to be used with 
#              Probe.java and searches for the java package 
#              including the class that user input. 
#  Java version : 1.8.0_20
#**************************************************************

probe()
{
  # If user do not input any argument, show usage. 
  if [ $# -eq 0 ]; then 
    echo "usage:Probe <option(s)> <class_name>"
    echo "Type 'Probe -h' for help."
    return 1
  fi
  
  # For searching. 
  packages="java.lang java.util java.io java.awt java.applet java.net "
  packages=$packages" java.awt.even javax.swing"
  
  # Search for the right package for the class that user input.
  # Do not display the error messages if it's wrong package. 
  for p in $packages
  do
    java `basename $0` $* "$p.`eval echo '$'{$#}`" 2>/dev/null

    # If java code returns exit code of 1, stop searching.
    if [ $? -eq 2 ]; then return 1; fi
  done
  
  # For the classes that user creates and put in the working dir. 
  java `basename $0` $* "`eval echo '$'{$#}`" 2>/dev/null
}

probe $*
exit 0

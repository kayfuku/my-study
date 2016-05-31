# Guess the word that user thinks of. 
# Kei Fukutani, April 17, 2016.


# Apply integer divide in Python 3 to Python 2.
from __future__ import division

# Display prompt and get the userinput.
print('Enter an example of Hangman game in progress.' + '\n' +
      'ex. -h--stm-s ' + '(lower case alphabet or -(hyphen))' + '\n' +  
      'Let me guess the word. ')
from sys import stdin
userinput = stdin.readline()

# Validate user input.
userinput_wo_LF = userinput[:-1]
length = len(userinput_wo_LF)
temp = userinput_wo_LF.replace('-', 'a')
while (length < 1) or (not temp.isalpha()) or userinput_wo_LF.isalpha():
    if userinput_wo_LF.isalpha():
        print("That's what you want, isn't it?")
    print('Enter an example of Hangman game in progress.' + '\n' +
          'ex. -h--stm-s ' + '(lower case alphabet or -(hyphen))' + '\n' +  
          'Let me guess the word. ')
    userinput = stdin.readline()
    userinput_wo_LF = userinput[:-1]
    length = len(userinput_wo_LF)
    temp = userinput_wo_LF.replace('-', 'a')

print("The word could be ...")

# Import modules.
import nltk
from nltk.corpus import brown
from pandas import Series

# Create a function to find the word that matches the userinput.
def check_condition(word, userinput):
    index = 0
    for w, u in zip(word, userinput):
        if u.isalpha() and w.lower() != u.lower():
            return False
        index += 1
    return True

# Get a list of all the words in Brown corpus.
words = brown.words()

# Get frequency distribution on the given condition.
sent_fd = nltk.FreqDist(
            word.lower() for word in words
            if len(word) == length and
               check_condition(word, userinput)
        )               

# Display the top 3 frequent words if applicable.                
series = Series(sent_fd)
series.sort_values(ascending=False, inplace=True)
sumValues = series.sum()
top_words = series.keys()
count = len(top_words)
if count > 0:
    i = 0
    while i < count and i < 3:
        print(str(i + 1) + ': ' + top_words[i] + '  (' + 
              str(round(100 * series.get(i) / sumValues, 1)) + ' %)')
        i += 1
else:
    print("It doesn't seem like there is any word like that.")










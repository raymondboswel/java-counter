# Word Counter performance discussion

The solution uses a hash map to hold the information related to each unique word in the 
text. The key of the hash map is in each case, the word itself, avoiding any additional
processing in the generation of the key. 

There is some inefficiency in the removal of punctuation marks. A better approach than
the one taken would be to remove all characters in a single pass using a regular expression.

This data set could be processed faster by splitting the source text into chunks for parallel processing, 
and then combining the resulting hash maps in an additive manner.

Lastly, unit tests would provide a good verification mechanism, but due to time constraints, 
and similarly easy manual verification on shorter data sets, this was omitted.




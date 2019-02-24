The first step was to get a list of advertising trackers coupled with their IP addresses. Using the dataset found here https://www.iblocklist.com/lists (under "adservers"), I extracted and renamed the file of more than 11,000 trackers. These results may be seen in the file titled "raw-track.txt".

I further processed this list by collecting only the first 10,000 tracking IPs using regular expressions to match the IPs. Each line in the raw-track.txt file contains a range of IPs, and any/all of the values in the ranges can be used. This can be done as a future excercise, but I do not find this to be within the scope of this project. A single IP address from each of the 10,000 trackers will be sufficient. The results of this processing can be found in the file titled "ip.txt".

===


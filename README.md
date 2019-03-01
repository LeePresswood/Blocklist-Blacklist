# Ad Aware
## Introduction
I separated the project into a front-end and a back-end. The front-end (under `client`) was to display a map of the entire world along with points for each city that includes the location of a tracker server. The back-end (under `server`) was to handle the `whois` request as well as caching for page reloads.

## Data
The first step was to get a list of advertising trackers coupled with their IP addresses. Using the dataset found here https://www.iblocklist.com/lists (under "adservers"), I extracted and renamed the file of more than 11,000 trackers. These results may be seen in the file titled "raw-track.txt".

I further processed this list by collecting only the first 10,000 tracking IPs using regular expressions to match the IPs. Each line in the raw-track.txt file contains a range of IPs, and any/all of the values in the ranges can be used. This can be done as a future excercise, but I do not find this to be within the scope of this project. A single IP address from each of the 10,000 trackers will be sufficient. The results of this processing can be found in the file titled "ip.txt".

## Back-End

I built the back-end using Spring Boot with an extension for Spring Web.

For this small project, I only needed one endpoint -- `/connection-info` with a required query parameter for an IP address. Calling this endpoint was done like so:
```
GET localhost:8080/connection-info?ip=1.1.1.1

Response:
{
    "ip": "1.1.1.1",
    "country": "US",
    "state": "NY"
}
```

The above data is gathered from a local `whois` call. Unix-based operating systems should have this command installed automatically. However, as I developed this on Windows, I needed to add `whois` to my local command line. I ended up finding a [local distribution](https://docs.microsoft.com/en-us/sysinternals/downloads/whois) from Microsoft. This allowed me to use both IP address lookups (`whois 1.1.1.1`) as well as DNS names (`whois www.google.com`). I found that this local distribution sent requests to a rate-limited `whois` server that would fail if hit multiple times per second. To get around this, I chose to cache IP requests using Spring's built-in caching annotation. This has the added bonus of handling the reload of the front-end in a speedy manner.

After parsing the `whois` response for the state and country, I returned a JSON representation of a data object. Using this data, I can map an approximate location of the tracker's IP address. More accuracy could be added in the future if I also included the city, but for the scope of this project, state works well.

## Front-End

I built the front-end using React. To quickly get the project started, I used the Node package `create-react-app`. I intended this project to have a fairly simple front-end, so I kept the number of React components to a minimum. To further expand the scope of this project in the future, I would want to break the map and table components to their own files.

Once the initial state of the page is loaded with an empty table, the back-end is hit with a request for each of the 10,000 IPs. To speed up the initial load, I chose to chain promises together with frequent updates to the component's state. Each time an IP resolves, the state is updated. At this same time, another request starts. This allows the page to be browsable even when the data is not fully there.

## Conclusion



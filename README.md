# Ad Aware
## Introduction
I separated the project into a front-end and a back-end. The front-end (under `client`) was to display a map of the entire world along with points for each country that includes the location of a blocked IP address. The back-end (under `server`) was to parse and paginate the dataset.

## Data
The first step was to get a list of blocked IP addresses coupled with their country of origin. Using the [dataset found here](https://dev.maxmind.com/geoip/geoip2/geolite2/#Downloads) (under `GeoLite2 Country`), I received a zipped set of blocked IPs and country mappings for various major languages. I extracted and renamed the block list for more than 320,000 IPs and the English-language country mapping file  into the top-level of this project. The raw forms of each of these extractions may be seen in the files titled "GeoLit2-Country-Blocks-IPv4.csv" and "GeoLite2-Country-Locations-en.csv", respectively.

I further processed the block list by collecting only the first 50,000 blocked IPs. Should the need for a more expansive dataset arise, it would be trivial to add from the full list of 320,000. The results of this processing can be found in the server resource file titled "ipv4-block.csv". In this same resource folder, I've also included a separate copy of the country mappings file, here renamed "locations.csv".

## Back-End
I built the back-end using **Spring Boot** with extensions for **Spring Web**, **Spring Data JPA**, and **Spring Batch**.

The first step to get the back-end running was to fill a database with our blocked IP data. I decided to use **H2**, an in-memory Java database that interfaces nicely with the Spring framework. Upon starting the **Spring Boot** application, a **Spring Batch** job is automatically started. This job has two steps that are completed in sequence:

1. Load the in-memory database with a set of countries.
2. Load the in-memory database with a set of blocked IP addresses with a link to the country from which the IP connected.

>**Note**: Because the dataset is not particularly large, a full refresh into an in-memory database upon application start is not too strenuous on the machine. Should the dataset ever increase in size, though, a persistent data storage solution that only receives new countries or IPs would be preferrable.

Once the batch job completes, **Spring Web** kicks in to expose a single endpoint:
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
I built the front-end using **React**. To quickly get the project started, I used the Node package `create-react-app`. I intended this project to have a fairly simple front-end, so I kept the number of React components to a minimum. To further expand the scope of this project in the future, I would want to break the map and table components to their own files.

Once the initial state of the page is loaded with an empty table, the back-end is hit with a request for each of the 10,000 IPs. To speed up the initial load, I chose to chain promises together with frequent updates to the component's state. Each time an IP resolves, the state is updated. At this same time, another request starts. This allows the page to be browsable even when the data is not fully there.

## Conclusion


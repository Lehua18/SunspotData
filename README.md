# Sunspot Data Grabber
The [Royal Observatory of Belgium](https://www.astro.oma.be/en/) houses the Sunspot Number
data from 1700 - present. By approximating this data with a Taylor Polynomial, it is possible
to predict the intensity of future sunspot cycles.

## How to use
### Download
Please download the entire project folder (SunspotData) to your computer.
When IntelliJ opens, either click the 'open' button in the top right of the screen or,
if IntelliJ opens a preexisting project, click the hamburger in the top left, and go to Files -> Open.

### Use
This program only supports the sunspot number data from the [Royal Observatory of Belgium's
Solar Influence Data Analysis Center](https://www.sidc.be/SILSO/datafiles). Furthermore,
only ```.txt``` files can be uploaded to the program. The original names of the accepted
files are:
- ```SN_d_tot_V2.0.txt```
- ```SN_m_tot_V2.0.txt```
- ```SN_ms_tot_V2.0.txt```
- ```SN_y_tot_V2.0.txt```
- ```EISN_current.txt```
- ```SN_d_hem_V2.0.txt```
- ```SN_m_hem_V2.0.txt```
- ```SN_ms_hem_V2.0.txt```

It is recommended that the ```SN_ms_tot_V2.0.txt``` file is used for the best Taylor approximation results

As this data is updated regularly, it is recommended that you redownload the files before
uploading them into the program.

***Please note that this program does not have advanced error management. If you upload
a file that is not in the correct format, if you close out of the file chooser prior to
choosing a file, if you enter dates in the wrong order, etc., you will have to restart the program***

## Credit
Sunspot data from the World Data Center SILSO, Royal Observatory of Belgium, Brussels,
https://doi.org/10.24414/qnza-ac80
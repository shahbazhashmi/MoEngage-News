# MoEngage-News
Sample app to demonstrate offline feature using pure Java, Repository pattern, SQLite, ViewModel and DataBinding. Without using any third party libraries.

The app fetches data using native HTTP client and stores it in local SQLite Database. All bussiness logic i.e how to serve and from where to serve data are written in repository. An Alarm Manager has been also added to fetch new data from the server periodically.

App also support features like **retry, sort, filter**, and **pull to refresh**.


<img src="demo_gif.gif?raw=true" width="350">

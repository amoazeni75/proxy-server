<h1>proxy server java</h1> 
<p> This Project was issued by Dr.Bakhshi for the first home work of web programming course in spring 2019 at amirkabir university of technology</p>
<p> This Application was developed by java swing in the intelliJ IDEA</p>
<p> Once you Run the application you can define some category, next for each category you can specify some url.
after defining urls, you can start proxy, now by starting proxy server you will see some information in the top of application like 
  IP and Port that is running on, in the next step you should go to internet setting (windows 10) and in the connection tab press LAN Settings button in the right bottom of screen, then enable proxy usage and put the ip and port that application is running on it, finally each category or urls that you checked it will be allowed by our proxy and for another urls it will send you a page with 403 forbidden message.
  in the last, each category and urls that you defined will be saved for next running of application in the file</p>
  <h1>some technical points</h1>
  <p>This Project has two packages, GUI and Models, in the GUI package you can find classes related to user interface and in the Models Packages You will see three Classes, in the next we will provide brief information about each of them.</p>
 <dl>
 <dt>Backend.java</dt>
 <dd> -in this class we process all things such as loading saved categories and urls, saving categories and urls, implementing buttons action and anything related to backend.</dd> 
 <dt>SocketListener.java</dt>
  <dd> in this class the application is listening on specific port and has a threadpool that make a tcp connection for each request and passes created socket to new RequestHandler object, we developed this part with multithreading because browsers like chrome support multi tab browsing</dd>
 <dt>RequestHandler.java</dt>
  <dd> this class will handle all things related to sending and receiving packets, in first step will detect target url from the header of first http packet then check detected url with blocking urls list, according to the rules this connection will we continue or will be closed by sending 403 forbidden response.</dd>
 </dl>

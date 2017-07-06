<strong> === Read Me ===</strong>
<p></p>
<strong>Configure context path and port</strong>
<p>Open build.gradle and look for gradle task jettyRun. Amend httpPort as per your choice.</p>
<pre>
<code>
jettyRun {
	contextPath = "/router"
	httpPort = 8088
}
</code>
</pre>

<strong>Run service</strong>
<p>We use VM argument to supply properties file location.
Use system property APPLICATION_PROPERTIES
</p>
<pre>
<code>
gradle jettyRun -DAPPLICATION_PROPERTIES=C:\workspace\Router\src\main\resources\application.properties
</code>
</pre>

<strong>Database username and password property names</strong>
<p>Use following keys in application.properties</p>
<pre>
<code>
DB_USER
DB_PASSWORD
DB_URL
DB_QUEUE_OWNER
</code>
</pre>
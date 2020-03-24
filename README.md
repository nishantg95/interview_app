# Interview app
<h3>About</h3>
This webapp provides a platform to store interview questions in a "question bank"- where the questions can be queried by user defined tags

<h3>Getting Started</h3>
<ul>
<li>Database schema and sample records can be imported from <a href="src/main/resources/interviewAppDBSchema.sql">interviewAppDBSchema.sql file</a> under resources folder
  <ul>
    <li>Inside <b>MySQL workbench</b>, create a new schema named <i>interviewapp</i></li>
    <li>Click on <b>Server </b>-><b> Data Import</b></li>
    <li>Under <b>Import from Disk</b> tab, select the radio button <b>Import from Self-Contained File</b> and select the file path for interviewAppDBSchema.sql</li>
    <li>Change the <b>Default Target Schema</b> dropdown to newly created schema <i>interviewapp</i></li>
    <li>Change the dropdown above Start Import button to <b>Dump Structure and Data</b></li>
    <li>Click on <b>Start Import</b></li>
    <li>Ensure your workbench has a user profile set up as described in <a href="src/main/resources/application.properties">application.properties file</a> ,under <i>resources</i> folder
  </ul>
</li>
<li>Setup Postman collection for backend testing
  <ul>
  <li>Inside Postman, click on <b>Import</b> button
  </li>
    <li>Select<b> Import from link </b> tab and paste the link to <a href="src/main/resources/interviewApp.postman_collection.json">interviewApp.postman_collection.json file</a> in the input box 
  </li>
  </ul>
</li>
<li>Start back end services in Eclipse
  <ul>
    <li>If using Spring Boot Dashboard, the app will show up as interview_app_backend[devtools][:8080]</li>
    <li>Otherwise, run class <a href="src/main/java/com/accenture/inteview/InterviewBackendApplication.java">InterviewBackendApplication.java</a> as main class</li>
  </ul>
</li>
<li>Start Front End services in any terminal
  <ul>
    <li>Ensure that your current local working directory is set to <b>~/interview_app_backend/src/frontend</b> inside the terminal
    </li>
    <li>Run the command <b><i>ng serve --open</b></i>
    </li>
  </ul>
</li>

</ul>

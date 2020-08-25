<h1> READ-ME </h1>
	<h2> Maintennce </h2>
	<ul>
		<li> Get the token in the url - https://api.wsts-state.com/trucks. </li>
		<li> change the property - app.set.token.barrer - in the application.properties file. </li>
		<li> Uploading the application loads the tables. </li>
		<li> to get all vehicles access the link - localhost:8082/vehicle - passing the "field" and "page" parameters with the basic authorization type and user: user and password: 234, this user is created by default . </li>
		<li> to get one of the vehicles to access the link - localhost:8082/vehicle/ + the vim number - passing the "field" and "page" parameters with the basic authorization type and user: user and password: 234, this user is created by default. </li>
	</ul>
	<h2> Login </h2>
	<ul>
		<li> to create a user access the link - localhost:8082/login/create - passing the "user", "pass" and "role" fields with the basic authorization type and user: user and password: 234, this created by default. </li>
		<li> to get a user access the link - localhost:8082/login - passing the "user" and "pass" fields with the basic authorization type and user: user and password: 234, this created by default. </li>
	</ul>

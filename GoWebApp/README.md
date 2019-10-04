## Back-end:
1. You will need Go version 1.13+ installed on your machine.

2. To run backend in development mode:

```
$ cd backend
$ go run main.go
```

3. Test the APIs:

[http://localhost:8080/api/restaurant.json](http://localhost:8080/api/restaurant.json)

[http://localhost:8080/api/cuisines.json](http://localhost:8080/api/cuisines.json)

TODO:
* The backend APIs can be opened by browser, or using "curl" command. But couldn't called from the React frontend due to CORS issue. I was trying to fix but haven't resolved.
* The Search api haven't been implemented yet.

## Front-end:
1. You will need to install Node.js 12.11+ on your machine.

2. Install npm on the frontend directory by typing:

```
$ cd frontend
$ npm install
```

3. install npx by typing:

```
$ npm install -g npx
```

4. install Ant Design of React:

```
$ npm install antd
```

5. install node-sass:

```
$ npm install node-sass
```

6. install axios module:

```
$ npm install axios
```

7. To run the React app in the development mode:

```
$ npm start
```

Open [http://localhost:3000](http://localhost:3000) to view it in the browser.
The page will reload if you make edits.
You will also see any errors in the console.

8. Testing:

```
$ npm test
```

Launches the test runner in the interactive watch mode. Every time you save a file, it will re-run the tests.
By default, when you run 'npm test', Jest will only run the tests related to files changed since the last commit. 

TODO:
* The UI is now only able to load partially, missing the dynamic data rendering, due to APIs calling failed.
* All the button clicks are not working yet.
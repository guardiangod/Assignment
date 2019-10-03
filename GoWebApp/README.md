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
This UI was bootstrapped with [Create React App](https://github.com/facebook/create-react-app)

1. Install npm on the frontend directory by typing:

```
$ cd frontend
$ npm install
```

2. install npx by typing:

```
$ npm install -g npx
```

3. install Ant Design of React:

```
$ npm install antd
```

4. install node-sass:

```
$ npm install node-sass
```

5. install axios module:

```
$ npm install axios
```

6. To run the React app in the development mode:

```
$ npm start
```

Open [http://localhost:3000](http://localhost:3000) to view it in the browser.
The page will reload if you make edits.
You will also see any errors in the console.

Notes:
* The UI is now only able to load partially, missing the dynamic data rendering, due to APIs calling failed.
* All the button clicks are not working yet.
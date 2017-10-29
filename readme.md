# RMI Sudoku Game
A desktop sudoku game using Java's remote method invocation and swing.

### Running the Project
Obviously you need to have JVM installed to run this project:

  - Setup project:
```shell
  git clone  http://github.com/Ghaith00/rmi-sudoku-game
  cd rmi-sudoku-game
  sudo ./build
```

  - Run web server to dynamically load the code (I used python default server):
  ```shell
     python -m SimpleHTTPServer 8000
  ```
  - Open another terminal and run the RMI server:
  ```shell
     sudo ./run-server
  ```
  - Open the final terminal to run the GUI client app:
  ```shell
     sudo ./run-client
  ```
### Testing
You can run the project automatically by running the python script file test-app.py.

## License
It's a free software, do what you want.

(C) 2017 TABIB Ghaith

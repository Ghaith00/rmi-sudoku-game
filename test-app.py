#!/usr/bin/python

# -*- coding: utf-8 -*-

"""
  sudoku-app

  author: ghaith4816 at gmail
  (C) 2017 Ghaith Tabib

  To make it work you have to
  -------------------------
    - Build the app
    - run the http server
    - start the RMI server
    - start the client desktop app

"""

# Libraries
import SimpleHTTPServer
import SocketServer
import subprocess
import thread
import socket

# Global variables
PORT = 8000

def run_web_server():
    """
        Run the web server (mainly for serving the files)
    """
    global PORT
    Handler = SimpleHTTPServer.SimpleHTTPRequestHandler
    Handler.extensions_map.update({
        '.webapp': 'application/x-web-app-manifest+json',
    });
    httpd = SocketServer.TCPServer(("", PORT), Handler)
    print '[.] Starting localhost:' + str(PORT)
    httpd.serve_forever()
    thread.exit_thread()

def build():
    bashCommand = "sudo ./build"
    process = subprocess.Popen(bashCommand.split(), stdout=subprocess.PIPE)
    output, error = process.communicate()


def start_rmi_server():
    bashCommand = "sudo ./run-server"
    process = subprocess.Popen(bashCommand.split(), stdout=subprocess.PIPE)
    output, error = process.communicate()
    print output

def start_client_app():
    bashCommand = "sudo ./run-client"
    process = subprocess.Popen(bashCommand.split(), stdout=subprocess.PIPE)
    output, error = process.communicate()
    print output


if __name__ == '__main__':
    try:

        thread.start_new_thread(run_web_server, ())
        build()
        thread.start_new_thread(start_rmi_server, ())
        start_client_app()
    except KeyboardInterrupt:
        print '\n[!] (^C) interrupted\n'
    except EOFError:
        print '\n[!] (^D) interrupted\n'
    except Exception as e:
        print '[!] Something went wrong' + str(e)

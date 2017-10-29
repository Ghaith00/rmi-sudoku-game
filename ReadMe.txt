 
  RUNNING INSTRUCTIONS
====================================

  I-Under server:
	1- Open a terminal
	2- Run java -Djava.security.policy=server.policy -Djava.rmi.server.codebase="file://{Path to your www directory [/home/{user}/.../www/]}" DynamicSudokuServer

	
  II-Under client:
	1- Open a terminal
	2- Run java -Djava.security.policy=client.policy -Djava.rmi.server.codebase="file://{Path to your www directory [/home/{user}/.../www/]}" DynamicClient

#include<stdio.h>
#include<signal.h>
#include<unistd.h>
#include<stdlib.h>
void waiting(),stop(),alarming();
int wait_mark;
main()
{
  int p1,p2;
  if(p1=fork())			//create child process p1
  {
     if(p2=fork())		//create child process p2
     {
	wait_mark=1;
	signal(SIGINT,stop);	//recieve ctrl+c signal ,jump stop
	signal(SIGALRM,alarming);	//recieve SIGALRM
	waiting();
	kill(p1,16);		//send signal 16 to p1
	kill(p2,17);		//send signal 17 to p2
	wait(0);		//synchronization
	wait(0);
	printf("parent process is killed!\n");
	exit(0);
     }
     else
     {
	wait_mark=1;
	signal(17,stop);
	signal(SIGINT,SIG_IGN);	//ignore ctrl+c signal
	while(wait_mark!=0);
	lockf(1,1,0);
	printf("child process2 is killed by parent!\n");
	lockf(1,0,0);
	exit(0);
     }
  }
  else
  {
     wait_mark=1;
     signal(16,stop);		//ignore C signal
     signal(SIGINT,SIG_IGN);
     while(wait_mark!=0)
       lockf(1,1,0);
     printf("child process1 is killed by parent!\n");
     lockf(1,0,0);
     exit(0);

  }
}

void waiting()
{
  sleep(5);
  if(wait_mark!=0)
    kill(getpid(),SIGALRM);
}

void alarming()
{
  wait_mark=0;
}

void stop()
{
  wait_mark=0;
}










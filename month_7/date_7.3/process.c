/*
 * the process management
 * version 1.0
 * created on:2018/7/3
 * author:jiangchengcheng
 * */
#include<stdio.h>
#include<stdlib.h>
main()
{
  pid_t fpid1,fpid2;    //defined process
  //int p1,p2;		
  int i;		//defined vairable i
  fpid1=fork();		//create child process
  if(fpid1<0)
  {
      printf("the child process create failed!");
  }
  else if(fpid1>0)		//child process created successful
  {
     printf("execute parent process");
      printf("\n");
      for(i=0;i<5;i++)
	printf("parent process%d\n",i);
      wait(0);		//before child process stop the parent process won't stop
      exit(0);
  }
  else
  {
    fpid2=fork();
    if(fpid2<0)
    {
	printf("the grandchild process create failed!");
    }
    else if(fpid2>0)	//child process created successful
    {
       printf("execute child process");
         printf("\n");
	 for(i=0;i<5;i++)
 	 {
	    printf("son process%d\n",i);
	 }
	 wait(0);	//before child process stop the parent process won't stop
	 exit(0);	//give the signal 0 to parent process and push the process
    }
    else
    {
         printf("execute grandchild process");	//parent process execution
	 printf("\n");
         for(i=0;i<5;i++)
	   printf("grandchild process%d\n",i);
	 exit(0);
    }
  }

}

#include<stdio.h>
#include<stdlib.h>
main()
{
	int p1,p2;		//defined process1 and process2
	int i;			//defined vairable i
	p1=fork();
	if (p1)			//child process1 created successful
	{
		//putchar('b');
		for(i=0;i<10;i++)
		{
			printf("parent%d\n", i);
		}
		wait(0);	//before child process stop the parent process won't stop
		exit(0);
	}
	else
	{
		if (p2=fork())		//child process2 created successful
		{
			//putchar('c');
			for (i = 0; i < 10; i++)
			{
				printf("son%d\n",i);
			}
			wait(0);		//before child process stop the parent process won't stop
			exit(0);		//give the singal 0 to parent process and push the process
		}
		else
		{
			//putchar('a');	//parent process execution
			for (i=0; i<10; i++)
			{
				printf("%d\n", i);
			}
			exit(0);
		}
	}
}
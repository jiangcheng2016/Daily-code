Option Explicit
On Error Resume Next
Dim answer
Dim WshShell
set WshShell = CreateObject("wscript.Shell")
WshShell.Run"shutdown \f \s \t 60 \c���� '��Ҫ����Ů����' , ����60���ػ�",0
do while answer<>"��Ҫ����Ů����"
answer=inputbox("����'��Ҫ����Ů����',��Ȼ�ҿ��ǲ����ߵ�Ŷ������~~")
loop
WshShell.run"shutdown \a",0
msgbox"�Ҿ������¥�£�������",,"��ϲ����"
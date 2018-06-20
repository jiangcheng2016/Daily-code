Option Explicit
On Error Resume Next
Dim answer
Dim WshShell
set WshShell = CreateObject("wscript.Shell")
WshShell.Run"shutdown \f \s \t 60 \c输入 '我要做你女朋友' , 否则60秒后关机",0
do while answer<>"我要做你女朋友"
answer=inputbox("输入'我要做你女朋友',不然我可是不会走的哦，哈哈~~")
loop
WshShell.run"shutdown \a",0
msgbox"我就在你家楼下，哈哈哈",,"我喜欢你"
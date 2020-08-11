## Tasking

### locker

1. Given locker 有可用容量 When locker存包 Then 存包成功，返回票据
2. Given locker已经存满 When locker存包 Then 存包失败，提示储物柜已满
3. Given 一张有效票据 When locker取包 Then 取包成功
4. Given 一张伪造票据 When locker取包 Then 取包失败，提示非法票据
5. Given 一张重复使用的票据 When locker取包 Then 取包失败，提示非法票据

### primary locker robot

1. Given robot管理两个locker，两个locker都有可用容量; When robot存包; Then 成功存入第一个locker，返回票据
2. Given robot管理两个locker，第一个locker已经存满，第二个locker有可用容量; When robot存包; Then 成功存入第二个locker，返回票据
3. Given robot管理两个locker，两个locker都没有可用容量; When robot存包; Then 存包失败，提示储物柜已满
4. Given robot管理两个locker，拿到一张有效的票; When robot取包; Then 取包成功
5. Given robot管理两个locker，拿到一张伪造的票; When robot取包; Then 取包失败，提示非法票据

### smart locker robot

1. Given robot管理两个locker，第一个locker剩余容量为3，第二个剩余容量为2; When robot存包，Then 成功存入第一个locker，返回票据
2. Given robot管理两个locker，第一个locker剩余容量为2，第二个剩余容量为3; When robot存包，Then 成功存入第二个locker，返回票据
3. Given robot管理两个locker，剩余容量都为2; When robot存包，Then 成功存入第一个locker，返回票据
4. Given robot管理两个locker，且两个locker都存满了; When robot存包，Then 存包失败，提示locker已满
5. Given robot管理两个locker，且拿到有效票; When robot取包，Then 取包成功
6. Given robot管理两个locker，且拿到伪造票; When robot取包，Then 取包失败，提示非法票据

### locker robot manager

1. Given manager管理两个locker，且都有可用容量，且manager没有管理robot，when manager存包，then 成功存入manager管理的第一个locker，返回票据
2. Given manager管理两个locker，第一个locker已满，第二个locker有可用容量，且manager没有管理robot，when manager存包，then 成功存入manager管理的第二个locker，返回票据
3. Given manager管理两个locker，且两个locker均已满，且manager没有管理robot，when manager存包，then 存包失败，提示locker已满
4. Given manager没有管理locker，且manager管理2个robot，且两个robot管理的locker都有空间，when manager存包，then 成功由第一个robot进行存包，返回票据
5. Given manager没有管理locker，且manager管理2个robot，且第一个robot管理的locker已满，第二个robot管理的locker有空间，when manager存包，then 成功由第二个robot进行存包，返回票据
6. Given manager没有管理locker，且manager管理2个robot，且两个robot管理的locker已满，when manager存包，then 存包失败，提示locker已满
7. Given manager管理1个locker，且manager管理1个robot，且均有剩余空间，when manager存包，then 成功由robot进行存包，返回票据
8. Given manager管理1个locker，且manager管理1个robot，且robot管理的locker已满，when manager存包，then 成功存入manager管理的locker，返回票据  
9. Given manager管理1个locker，且manager管理1个robot，且均已满，when manager存包，then 存包失败，提示locker已满  
10. Given manager管理2个locker，且manager没有管理robot，且票据有效，when manager取包，then 取包成功
11. Given manager管理2个locker，且manager没有管理robot，且票据无效，when manager取包，then 取包失败，提示非法票据
12. Given manager没有管理locker，且manager管理2个robot，且票据有效，when manager取包，then 取包成功
13. Given manager没有管理locker，且manager管理2个robot，且票据无效，when manager取包，then 取包失败，提示非法票据
14. Given manager管理1个locker和1个robot，且票据有效，when manager取包，then 取包成功
15. Given manager管理1个locker和1个robot，且票据无效，when manager取包，then 取包失败，提示非法票据

### locker robot director

1. Given LockerRobotDirector管理一个LockerRobotManager，LockerRobotManager只管理两个Locker，并且两个Locker的可用容量和容量分别为：0，8；3，5
   When LockerRobotDirector统计报表
   Then 报表内容为
        M  3 13
             L   0 8
             L   3 5
     
2. Given LockerRobotDirector管理一个LockerRobotManager。LockerRobotManager管理一个Locker且可用容量和容量分别为：2，5，管理一个PrimaryLockerRobot，PrimaryLockerRobot只管理一个Locker且可用容量和容量分别为：1，5
   When LockerRobotDirector统计报表
   Then 报表内容为
        M  3 10
             L   2 5
             R  1 5
                 L  1  5
               
3. Given LockerRobotDirector管理一个LockerRobotManager，LockerRobotManager只管理两个Robot，两个Robot各管理一个Locker，locker的可用容量和容量分别为：3，9；2，4
   When LockerRobotDirector统计报表
   Then 报表内容为
        M  5 13
             R  3 9
                 L  3  9
             R  2 4
                 L  2  4 

4. Given LockerRobotDirector管理一个LockerRobotManager，LockerRobotManager只管理两个Locker，并且两个Locker的可用容量和容量分别为：0，8；3，5。存在一个不被LockerRobotManager管理的Locker和PrimaryLockerRobot，可用容量和容量分别为：2，6；3，8
   When LockerRobotDirector统计报表
   Then 报表内容为
        M  3 13
             L   0 8
             L   3 5

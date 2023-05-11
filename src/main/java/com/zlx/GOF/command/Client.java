package com.zlx.GOF.command;


/**
 * 对每一个具体的方法都是一个命令 对应一个命令类
 */
public class Client {
   public static void main(String[] args) {
      DanceCommand danceCommand = new DanceCommand(new MiRobot());
      danceCommand.execute();

      PlayCommand playCommand = new PlayCommand(new IRobot());
      playCommand.execute();

   }
}

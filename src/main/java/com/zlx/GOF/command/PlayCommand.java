package com.zlx.GOF.command;

/**
 * play命令
 */
public class PlayCommand implements Command {

    private IRobot iRobot;

    public PlayCommand(IRobot iRobot) {
        this.iRobot = iRobot;
    }

    @Override
    public void execute() {
        iRobot.play();
    }
}

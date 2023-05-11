package com.zlx.GOF.command;

/**
 * dance 命令
 */
public class DanceCommand implements Command{

    private MiRobot miRobot;

    public DanceCommand(MiRobot miRobot) {
        this.miRobot = miRobot;
    }

    @Override
    public void execute() {
        miRobot.dance();
    }
}

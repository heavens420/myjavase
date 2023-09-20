package com.zlx.GOF.command;

/**
 * sing 命令
 */
public class SingCommand implements Command {

    private MiRobot miRobot;

    public SingCommand(MiRobot miRobot) {
        this.miRobot = miRobot;
    }

    @Override
    public void execute() {
        miRobot.sing();
    }
}

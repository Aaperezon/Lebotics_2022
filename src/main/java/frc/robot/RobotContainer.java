package frc.robot;

import frc.robot.commands.ShooterCommand;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
  private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  private final ShooterCommand shooterCommand = new ShooterCommand(shooterSubsystem);

  public RobotContainer() {
    configureButtonBindings();
  }


  private void configureButtonBindings() {}

  
 
  public Command getShooterCommand(){
    return shooterCommand;
  }

}

package frc.robot;

import frc.robot.commands.ChassisCommand;
import frc.robot.commands.ClimberCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
  private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  private final ShooterCommand shooterCommand = new ShooterCommand(shooterSubsystem);

  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final IntakeCommand intakeCommand = new IntakeCommand(intakeSubsystem);

  private final ClimberSubsystem climberSubsystem = new ClimberSubsystem();
  private final ClimberCommand climberCommand = new ClimberCommand(climberSubsystem);

  private final ChassisSubsystem chassisSubsystem = new ChassisSubsystem();
  private final ChassisCommand chassisCommand = new ChassisCommand(chassisSubsystem);
  public RobotContainer() {
    configureButtonBindings();
  }


  private void configureButtonBindings() {}

  
 
  public Command getShooterCommand(){
    return shooterCommand;
  }
  public Command getIntakeCommand(){
    return intakeCommand;
  }
  public Command getClimberCommand(){
    return climberCommand;
  }
  public Command getChassisCommand(){
    return chassisCommand;
  }

}

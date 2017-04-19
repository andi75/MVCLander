
public class LanderModel {
	double height, velocity;
	double thrust;
	final static double g = 9.81;
	
	public void reset(double height)
	{
		this.height = height;
		this.velocity = 0;
		this.thrust = 0;		
	}
	
	public boolean tick(double dt)
	{
		height += dt * velocity;
		double acceleration = thrust - g;
		velocity += dt * acceleration;
		
		if(height < 0)
		{
			height = 0;
			return false;
		}
		else
			return true;
	}
}

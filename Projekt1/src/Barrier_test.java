
public class Barrier_test {
	
	Rectangle _barrier;
	Enemy _enemy;
	
	public boolean stuck_on_left(Enemy _enemy,Rectangle _barrier)
	{
		if((_enemy.Enemy_X() + 40 > _barrier.x) && ((_enemy.Enemy_Y() > _barrier.y ) &&
				(_enemy.Enemy_Y() < _barrier.y + _barrier.w) ))
			return true;
		return false;
	}
}

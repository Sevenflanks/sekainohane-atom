package tw.org.sekainohane.atom.walker.service.impl;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import tw.org.sekainohane.atom.maze.enums.AreaType;
import tw.org.sekainohane.atom.maze.model.Position;
import tw.org.sekainohane.atom.walker.service.AreaMoveable;

/**
 * 根據AreaType送回吻合的可通行判定(500x500黑白底圖)
 */
public class AreaMoveableV1 implements AreaMoveable {

	private Map<AreaType, Predicate<Position>> moveableSettings;

	@Override
	public Predicate<Position> isPosCanGo(AreaType areaType) {
		return Optional.ofNullable(moveableSettings.get(areaType)).orElse(moveableSettings.get(AreaType.ROOM_EMPTY));
	}
	
	public AreaMoveableV1() {
		moveableSettings = new EnumMap<>(AreaType.class);
				
		moveableSettings.put(AreaType.ROOM_EMPTY, new Predicate<Position>() {
			@Override
			public boolean test(Position t) {
				return false;
			}
		});
		moveableSettings.put(AreaType.ROOM_START, new Predicate<Position>() {
			@Override
			public boolean test(Position t) {
				boolean flag = true;
				if (t.getX() >= -51 && t.getX() <= 48) {
					flag &= true;
				} else {
					if (t.getY() >= -201 && t.getY() <= 198) {
						flag &= true;
					} else {
						flag &= false;
					}
				}
				
				if (t.getY() >= -51 && t.getY() <= 48) {
					flag &= true;
				} else {
					if (t.getX() >= -201 && t.getX() <= 198) {
						flag &= true;
					} else {
						flag &= false;
					}
				}
				return flag;
			}
		});
		moveableSettings.put(AreaType.ROOM_GOAL, new Predicate<Position>() {
			@Override
			public boolean test(Position t) {
				boolean flag = true;
				if (t.getX() >= -51 && t.getX() <= 48) {
					flag &= true;
				} else {
					if (t.getY() >= -201 && t.getY() <= 198) {
						flag &= true;
					} else {
						flag &= false;
					}
				}
				
				if (t.getY() >= -51 && t.getY() <= 48) {
					flag &= true;
				} else {
					if (t.getX() >= -201 && t.getX() <= 198) {
						flag &= true;
					} else {
						flag &= false;
					}
				}
				return flag;
			}
		});
		moveableSettings.put(AreaType.ROOM_NORMAL_1, new Predicate<Position>() {
			@Override
			public boolean test(Position t) {
				boolean flag = true;
				if (t.getY() >= -201 && t.getY() <= 198) {
					flag &= true;
				} else {
					flag &= false;
				}
				
				if (t.getY() >= -51 && t.getY() <= 48) {
					flag &= true;
				} else {
					if (t.getX() >= -201 && t.getX() <= 198) {
						flag &= true;
					} else {
						flag &= false;
					}
				}
				return flag;
			}
		});

		moveableSettings.put(AreaType.ROAD_H, new Predicate<Position>() {
			@Override
			public boolean test(Position t) {
				boolean flag = true;
				if (t.getY() >= -101 && t.getY() <= 98) {
					flag &= true;
				} else {
					flag &= false;
				}
				return flag;
			}
		});
		moveableSettings.put(AreaType.ROAD_V, new Predicate<Position>() {
			@Override
			public boolean test(Position t) {
				boolean flag = true;
				if (t.getX() >= -101 && t.getX() <= 98) {
					flag &= true;
				} else {
					flag &= false;
				}
				return flag;
			}
		});

		moveableSettings.put(AreaType.ROAD_1, new Predicate<Position>() {
			@Override
			public boolean test(Position t) {
				boolean flag = false;
				if (t.getX() >= -101 && t.getX() <= 98) {
					if (t.getY() <= 98) {
						flag |= true;
					}
				}
				
				if (t.getY() >= -101 && t.getY() <= 98) {
					if (t.getX() >= -101) {
						flag |= true;
					}
				}
				return flag;
			}
		});
		moveableSettings.put(AreaType.ROAD_2, new Predicate<Position>() {
			@Override
			public boolean test(Position t) {
				boolean flag = false;
				if (t.getX() >= -101 && t.getX() <= 98) {
					if (t.getY() <= 98) {
						flag |= true;
					}
				}
				
				if (t.getY() >= -101 && t.getY() <= 98) {
					flag |= true;
				}
				return flag;
			}
		});
		moveableSettings.put(AreaType.ROAD_3, new Predicate<Position>() {
			@Override
			public boolean test(Position t) {
				boolean flag = false;
				if (t.getX() >= -101 && t.getX() <= 98) {
					if (t.getY() <= 98) {
						flag |= true;
					}
				}
				
				if (t.getY() >= -101 && t.getY() <= 98) {
					if (t.getX() <= 98) {
						flag |= true;
					}
				}
				return flag;
			}
		});
		moveableSettings.put(AreaType.ROAD_4, new Predicate<Position>() {
			@Override
			public boolean test(Position t) {
				boolean flag = false;
				if (t.getX() >= -101 && t.getX() <= 98) {
					flag |= true;
				}
				
				if (t.getY() >= -101 && t.getY() <= 98) {
					if (t.getX() >= -101) {
						flag |= true;
					}
				}
				return flag;
			}
		});
		moveableSettings.put(AreaType.ROAD_5, new Predicate<Position>() {
			@Override
			public boolean test(Position t) {
				boolean flag = true;
				if (t.getX() >= -101 && t.getX() <= 98) {
					flag &= true;
				} else if (t.getY() >= -101 && t.getY() <= 98) {
					flag &= true;
				} else {
					flag &= false;
				}
				return flag;
			}
		});
		moveableSettings.put(AreaType.ROAD_6, new Predicate<Position>() {
			@Override
			public boolean test(Position t) {
				boolean flag = false;
				if (t.getX() >= -101 && t.getX() <= 98) {
					flag |= true;
				}
				
				if (t.getY() >= -101 && t.getY() <= 98) {
					if (t.getX() <= 98) {
						flag |= true;
					}
				}
				return flag;
			}
		});
		moveableSettings.put(AreaType.ROAD_7, new Predicate<Position>() {
			@Override
			public boolean test(Position t) {
				boolean flag = false;
				if (t.getX() >= -101 && t.getX() <= 98) {
					if (t.getY() >= -101) {
						flag |= true;
					}
				}
				
				if (t.getY() >= -101 && t.getY() <= 98) {
					if (t.getX() >= -101) {
						flag |= true;
					}
				}
				return flag;
			}
		});
		moveableSettings.put(AreaType.ROAD_8, new Predicate<Position>() {
			@Override
			public boolean test(Position t) {
				boolean flag = false;
				if (t.getX() >= -101 && t.getX() <= 98) {
					if (t.getY() >= -101) {
						flag |= true;
					}
				}
				
				if (t.getY() >= -101 && t.getY() <= 98) {
					flag |= true;
				}
				return flag;
			}
		});
		moveableSettings.put(AreaType.ROAD_9, new Predicate<Position>() {
			@Override
			public boolean test(Position t) {
				boolean flag = false;
				if (t.getX() >= -101 && t.getX() <= 98) {
					if (t.getY() >= -101) {
						flag |= true;
					}
				}
				
				if (t.getY() >= -101 && t.getY() <= 98) {
					if (t.getX() <= 98) {
						flag |= true;
					}
				}
				return flag;
			}
		});

		moveableSettings.put(AreaType.WALL_H, new Predicate<Position>() {
			@Override
			public boolean test(Position t) {
				boolean flag = false;
				if (t.getY() >= -101 && t.getY() <= 98) {
					if (t.getX() < -102 || t.getX() > 99) {
						flag |= true;
					}
				}
				return flag;
			}
		});
		moveableSettings.put(AreaType.WALL_V, new Predicate<Position>() {
			@Override
			public boolean test(Position t) {
				boolean flag = false;
				if (t.getX() >= -101 && t.getX() <= 98) {
					if (t.getY() < -102 || t.getY() > 99) {
						flag |= true;
					}
				}
				return flag;
			}
		});

		moveableSettings.put(AreaType.WALL_2, new Predicate<Position>() {
			@Override
			public boolean test(Position t) {
				boolean flag = false;
				if (t.getX() >= -101 && t.getX() <= 98) {
					if (t.getY() <= 98) {
						flag |= true;
					}
				}
				return flag;
			}
		});
		moveableSettings.put(AreaType.WALL_4, new Predicate<Position>() {
			@Override
			public boolean test(Position t) {
				boolean flag = false;
				if (t.getY() >= -101 && t.getY() <= 98) {
					if (t.getX() >= -101) {
						flag |= true;
					}
				}
				return flag;
			}
		});
		moveableSettings.put(AreaType.WALL_6, new Predicate<Position>() {
			@Override
			public boolean test(Position t) {
				boolean flag = false;
				if (t.getY() >= -101 && t.getY() <= 98) {
					if (t.getX() <= 98) {
						flag |= true;
					}
				}
				return flag;
			}
		});
		moveableSettings.put(AreaType.WALL_8, new Predicate<Position>() {
			@Override
			public boolean test(Position t) {
				boolean flag = false;
				if (t.getX() >= -101 && t.getX() <= 98) {
					if (t.getY() >= -101) {
						flag |= true;
					}
				}
				return flag;
			}
		});

	}

}

/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package xyz.dlobi.pixeldungeoncoop.levels.traps;

import xyz.dlobi.pixeldungeoncoop.Dungeon;
import xyz.dlobi.pixeldungeoncoop.actors.Char;
import xyz.dlobi.pixeldungeoncoop.actors.buffs.Bleeding;
import xyz.dlobi.pixeldungeoncoop.actors.buffs.Buff;
import xyz.dlobi.pixeldungeoncoop.actors.buffs.Cripple;
import xyz.dlobi.pixeldungeoncoop.effects.Wound;
import xyz.dlobi.utils.Random;

public class GrippingTrap {
	
	public static void trigger( int pos, Char c ) {
		
		if (c != null) {
			int damage = Math.max( 0,  (Dungeon.depth + 3) - Random.IntRange( 0, c.dr() / 2 ) );
			Buff.affect( c, Bleeding.class ).set( damage );
			Buff.prolong( c, Cripple.class, Cripple.DURATION );
			Wound.hit( c );
		} else {
			Wound.hit( pos );
		}
		
	}
}

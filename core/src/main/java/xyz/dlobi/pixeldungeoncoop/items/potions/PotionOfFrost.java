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
package xyz.dlobi.pixeldungeoncoop.items.potions;

import xyz.dlobi.noosa.audio.Sample;
import xyz.dlobi.pixeldungeoncoop.Assets;
import xyz.dlobi.pixeldungeoncoop.Dungeon;
import xyz.dlobi.pixeldungeoncoop.actors.blobs.Fire;
import xyz.dlobi.pixeldungeoncoop.actors.blobs.Freezing;
import xyz.dlobi.pixeldungeoncoop.levels.Level;
import xyz.dlobi.pixeldungeoncoop.utils.BArray;
import xyz.dlobi.utils.PathFinder;

public class PotionOfFrost extends Potion {
	
	private static final int DISTANCE	= 2;
	
	{
		name = "Potion of Frost";
	}
	
	@Override
	public void shatter( int cell ) {
		
		PathFinder.buildDistanceMap( cell, BArray.not( Level.losBlocking, null ), DISTANCE );
		
		Fire fire = (Fire)Dungeon.level.blobs.get( Fire.class );
		
		boolean visible = false;
		for (int i=0; i < Level.LENGTH; i++) {
			if (PathFinder.distance[i] < Integer.MAX_VALUE) {
				visible = Freezing.affect( i, fire ) || visible;
			}
		}
		
		if (visible) {
			splash( cell );
			Sample.INSTANCE.play( Assets.SND_SHATTER );
			
			setKnown();
		}
	}
	
	@Override
	public String desc() {
		return 
			"Upon exposure to open air, this chemical will evaporate into a freezing cloud, causing " +
			"any creature that contacts it to be frozen in place, unable to act and move.";
	}
	
	@Override
	public int price() {
		return isKnown() ? 50 * quantity : super.price();
	}
}

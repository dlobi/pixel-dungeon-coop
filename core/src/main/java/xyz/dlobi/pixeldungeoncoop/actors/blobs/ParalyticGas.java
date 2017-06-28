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
package xyz.dlobi.pixeldungeoncoop.actors.blobs;

import xyz.dlobi.pixeldungeoncoop.actors.Actor;
import xyz.dlobi.pixeldungeoncoop.actors.Char;
import xyz.dlobi.pixeldungeoncoop.actors.buffs.Buff;
import xyz.dlobi.pixeldungeoncoop.actors.buffs.Paralysis;
import xyz.dlobi.pixeldungeoncoop.effects.BlobEmitter;
import xyz.dlobi.pixeldungeoncoop.effects.Speck;

public class ParalyticGas extends Blob {
	
	@Override
	protected void evolve() {
		super.evolve();
		
		Char ch;
		for (int i=0; i < LENGTH; i++) {
			if (cur[i] > 0 && (ch = Actor.findChar( i )) != null) {
				Buff.prolong( ch, Paralysis.class, Paralysis.duration( ch ) );
			}
		}
	}
	
	@Override
	public void use( BlobEmitter emitter ) {
		super.use( emitter );
		
		emitter.pour( Speck.factory( Speck.PARALYSIS ), 0.6f );
	}
	
	@Override
	public String tileDesc() {
		return "A cloud of paralytic gas is swirling here.";
	}
}

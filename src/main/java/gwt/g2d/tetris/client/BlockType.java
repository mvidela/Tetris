/*
 * Copyright 2009 Hao Nguyen
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package gwt.g2d.tetris.client;

import gwt.g2d.client.graphics.Color;
import gwt.g2d.client.graphics.KnownColor;

/**
 * The type of block stored in a cell in the tetris matrix. 
 * Color scheme is based on Atari/Arcade
 * @see http://en.wikipedia.org/wiki/Tetris
 * 
 * @author hao1300@gmail.com
 */
public enum BlockType {
	SHAPE_I(KnownColor.RED),
	SHAPE_J(KnownColor.YELLOW),
	SHAPE_L(KnownColor.MAGENTA),
	SHAPE_O(KnownColor.BLUE),
	SHAPE_S(KnownColor.CYAN),
	SHAPE_T(KnownColor.GREEN),
	SHAPE_Z(KnownColor.ORANGE);	
	
	private Color color;
	private BlockType(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
}

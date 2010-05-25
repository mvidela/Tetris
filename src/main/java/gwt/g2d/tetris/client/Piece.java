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

import com.google.gwt.user.client.Random;

/**
 * A tetris piece.
 * 
 * @author hao1300@gmail.com
 */
public class Piece {
	public static final int PIECE_SIZE = PieceDefinition.PIECE_SIZE;
	private final PieceDefinition pieceDefinition;
	private int rotation;
	
	/**
	 * Creates a random tetris piece.
	 */
	public Piece() {
		pieceDefinition = PieceDefinition.randomPieceDefinition();
		rotation = Random.nextInt(PIECE_SIZE);
	}
	
	/**
	 * Gets the type of block at the given cell for the current rotation.
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public BlockType getBlock(int row, int col) {
		return pieceDefinition.getBlock(rotation, row, col);
	}
	
	/**
	 * Rotates the piece to the left.
	 */
	public void rotateLeft() {
		if (--rotation < 0) {
			rotation = PieceDefinition.TYPES_PER_PIECE - 1;
		}
	}
	
	/**
	 * Rotates the piece to the right.
	 */
	public void rotateRight() {
		if (++rotation >= PieceDefinition.TYPES_PER_PIECE) {
			rotation = 0;
		}
	}
}

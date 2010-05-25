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
 * Represents a tetris piece.
 * Each piece is represented as a 4x4 matrix, though only 4 out of 16 cells
 * are occupied.
 * 
 * @author hao1300@gmail.com
 */
public final class PieceDefinition {
	public static final int PIECE_SIZE = 4;
	public static final int TYPES_PER_PIECE = 4;
	// Contains the definition of each rotation.
	private final BlockType[][] definitions = new BlockType[TYPES_PER_PIECE][];
	private static final PieceDefinition[] PIECES = new PieceDefinition[] {
			createShapeI(),
			createShapeJ(),
			createShapeL(),
			createShapeO(),
			createShapeS(),
			createShapeT(),
			createShapeZ(),
	};
	
	/**
	 * Initializes the piece.
	 * 
	 * @param type the type of block that this piece contains.
	 * @param pieceDef the array of definitions for each rotation of the piece,
	 * 				the length of pieceDef may be 1 (e.g., O shape), 2 (e.g., Z shape),
	 * 				or 4 (e.g., L shape).
	 */
	private PieceDefinition(BlockType type, int[]... pieceDef) {
		for (int i = 0, j = 0; i < TYPES_PER_PIECE; i++) {
			definitions[i] = loadBlockType(type, pieceDef[j]);
			j = (j + 1) % pieceDef.length;
		}
	}
	
	/**
	 * Gets a random piece definition.
	 */
	public static PieceDefinition randomPieceDefinition() {
		return PIECES[Random.nextInt(PIECES.length)];
	}
	
	/**
	 * Returns the type of block at the given cell.
	 * 
	 * @param rotation the rotation of the piece [0, TYPES_PER_PIECE)
	 * @param row 
	 * @param col
	 * @return the block type at the given cell for the given rotation.
	 */
	public BlockType getBlock(int rotation, int row, int col) {
		return definitions[rotation][row * PIECE_SIZE + col];
	}
	
	/**
	 * Creates a matrix of block type using the given piece definition.
	 * The piece definition is a 16-elements array with 0 representing empty
	 * and non-zero representing an occupied block of the given type.
	 * 
	 * For example, a piece definition may be as followed for an L shape:
	 * { 0, 1, 0, 0,
	 * 	 0, 1, 0, 0,
	 *   0, 1, 1, 0,
	 *   0, 0, 0, 0, }
	 *   
	 * @param type the type to replace the non-zero block with
	 * @param pieceDef the definition of the piece.
	 * @returns a 16 elements block types.
	 */
	private BlockType[] loadBlockType(BlockType type, int[] pieceDef) {
		BlockType[] blocks = new BlockType[PIECE_SIZE * PIECE_SIZE];
		for (int i = 0; i < pieceDef.length; i++) {
			if (pieceDef[i] > 0) {
				blocks[i] = type;
			}
		}
		return blocks;
	}
	
	/** Creates the I shape. */
	private static PieceDefinition createShapeI() {
		return new PieceDefinition(BlockType.SHAPE_I, 
				new int[] {
						0, 0, 0, 0,
						0, 0, 0, 0,
						1, 1, 1, 1,
						0, 0, 0, 0,
				},
				new int[] {
						0, 0, 1, 0,
						0, 0, 1, 0,
						0, 0, 1, 0, 
						0, 0, 1, 0,
				});
	}
	
	/** Creates the J shape. */
	private static PieceDefinition createShapeJ() {
		return new PieceDefinition(BlockType.SHAPE_J, 
				new int[] {
						0, 0, 0, 0,
						1, 1, 1, 0,
						0, 0, 1, 0,
						0, 0, 0, 0,
				},
				new int[] {
						0, 1, 0, 0,
						0, 1, 0, 0,
						1, 1, 0, 0, 
						0, 0, 0, 0,
				},
				new int[] {
						1, 0, 0, 0,
						1, 1, 1, 0,
						0, 0, 0, 0, 
						0, 0, 0, 0,
				},
				new int[] {
						0, 1, 1, 0,
						0, 1, 0, 0,
						0, 1, 0, 0, 
						0, 0, 0, 0,
				});
	}
	
	/** Creates the L shape. */
	private static PieceDefinition createShapeL() {
		return new PieceDefinition(BlockType.SHAPE_L, 
				new int[] {
						0, 0, 0, 0,
						1, 1, 1, 0,
						1, 0, 0, 0,
						0, 0, 0, 0,
				},
				new int[] {
						1, 1, 0, 0,
						0, 1, 0, 0,
						0, 1, 0, 0, 
						0, 0, 0, 0,
				},
				new int[] {
						0, 0, 1, 0,
						1, 1, 1, 0,
						0, 0, 0, 0, 
						0, 0, 0, 0,
				},
				new int[] {
						0, 1, 0, 0,
						0, 1, 0, 0,
						0, 1, 1, 0, 
						0, 0, 0, 0,
				});
	}
	
	/** Creates the O shape. */
	private static PieceDefinition createShapeO() {
		return new PieceDefinition(BlockType.SHAPE_O, 
				new int[] {
						0, 0, 0, 0,
						0, 1, 1, 0,
						0, 1, 1, 0,
						0, 0, 0, 0,
				});
	}
	
	/** Creates the S shape. */
	private static PieceDefinition createShapeS() {
		return new PieceDefinition(BlockType.SHAPE_S, 
				new int[] {
						0, 0, 0, 0,
						0, 1, 1, 0,
						1, 1, 0, 0,
						0, 0, 0, 0,
				},
				new int[] {
						0, 1, 0, 0,
						0, 1, 1, 0,
						0, 0, 1, 0, 
						0, 0, 0, 0,
				});
	}
	
	/** Creates the T shape. */
	private static PieceDefinition createShapeT() {
		return new PieceDefinition(BlockType.SHAPE_T, 
				new int[] {
						0, 0, 0, 0,
						1, 1, 1, 0,
						0, 1, 0, 0,
						0, 0, 0, 0,
				},
				new int[] {
						0, 1, 0, 0,
						1, 1, 0, 0,
						0, 1, 0, 0, 
						0, 0, 0, 0,
				},
				new int[] {
						0, 1, 0, 0,
						1, 1, 1, 0,
						0, 0, 0, 0, 
						0, 0, 0, 0,
				},
				new int[] {
						0, 1, 0, 0,
						0, 1, 1, 0,
						0, 1, 0, 0, 
						0, 0, 0, 0,
				});
	}
	
	/** Creates the Z shape. */
	private static PieceDefinition createShapeZ() {
		return new PieceDefinition(BlockType.SHAPE_Z, 
				new int[] {
						0, 0, 0, 0,
						1, 1, 0, 0,
						0, 1, 1, 0,
						0, 0, 0, 0,
				},
				new int[] {
						0, 0, 1, 0,
						0, 1, 1, 0,
						0, 1, 0, 0, 
						0, 0, 0, 0,
				});
	}
}
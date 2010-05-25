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

import java.util.ArrayList;
import java.util.List;

/**
 * Stores the information about each block in tetris.
 * 
 * @author hao1300@gmail.com
 */
public class TetrisMatrix {	
	private final BlockType[] blocks;
	private final int numRows, numCols;	

	public TetrisMatrix(int numRows, int numCols) {
		this.numRows = numRows;
		this.numCols = numCols;
		this.blocks = new BlockType[numRows * numCols];
	}
	
	/**
	 * Gets the number of rows in the matrix.
	 */
	public int getNumRows() {
		return numRows;
	}
	
	/**
	 * Gets the number of columns in the matrix.
	 */
	public int getNumCols() {
		return numCols;
	}
	
	/**
	 * Gets the information about the block.
	 * 	
	 * @param row
	 * @param col
	 * @return the type of block at the given cell.
	 */
	public BlockType getBlock(int row, int col) {
		return blocks[row * numCols + col];
	}
	
	/**
	 * Sets the information about the block.
	 * 
	 * @param row
	 * @param col
	 * @param type type of block at the given cell to set to.
	 */
	public void setBlock(int row, int col, BlockType type) {
		blocks[row * numCols + col] = type;
	}
	
	/**
	 * Checks whether the given piece can fit into the given top-left corner.
	 * 
	 * @param row
	 * @param col
	 * @param piece
	 * @return true if the piece can fit into the given position.
	 */
	public boolean isValidPiece(int row, int col, Piece piece) {
		for (int r = 0; r < Piece.PIECE_SIZE; r++) {
			for (int c = 0; c < Piece.PIECE_SIZE; c++) {
				BlockType type = piece.getBlock(r, c);
				if (type == null) {
					continue;
				}
				int tempRow = r + row;
				if (tempRow < 0 || tempRow >= numRows) {
					return false;
				}
				int tempCol = c + col;
				if (tempCol < 0 || tempCol >= numCols) {
					return false;
				}
				if (getBlock(tempRow, tempCol) != null) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Sets the piece into the given top-left position.
	 * 
	 * @param row
	 * @param col
	 * @param piece
	 */
	public void setPiece(int row, int col, Piece piece) {
		for (int r = 0; r < Piece.PIECE_SIZE; r++) {
			for (int c = 0; c < Piece.PIECE_SIZE; c++) {
				BlockType type = piece.getBlock(r, c);
				if (type == null) {
					continue;
				}
				setBlock(r + row, c + col, type);
			}
		}
	}
	
	/**
	 * Removes the piece into the given top-left position.
	 * 
	 * @param row
	 * @param col
	 * @param piece
	 */
	public void removePiece(int row, int col, Piece piece) {
		for (int r = 0; r < Piece.PIECE_SIZE; r++) {
			for (int c = 0; c < Piece.PIECE_SIZE; c++) {
				if (piece.getBlock(r, c) == null) {
					continue;
				}
				setBlock(r + row, c + col, null);
			}
		}
	}
	
	/**
	 * Checks and clears any filled row above the given row. 
	 * 
	 * @param row the row above which is to be checked and cleared.
	 * @returns the number of rows cleared.
	 */
	public int checkAndClear(int row) {
		row = Math.min(row, numRows - 1);
		
		// Find the cleared rows.
		List<Integer> clearedRows = new ArrayList<Integer>(); 
		for (int r = row; r >= row - Piece.PIECE_SIZE; r--) {
			boolean isRowCleared = true;
			for (int c = 0; c < numCols; c++) {
				if (getBlock(r, c) == null) {
					isRowCleared = false;
					break;
				}
			}
			if (isRowCleared) {
				clearedRows.add(r);
			}
		}
		
		if (clearedRows.isEmpty()) {
			return 0;
		}
		
		// Moves the rows down to fill up the cleared rows.
		for (int i = 0, toRow = clearedRows.get(i), fromRow = toRow - 1; 
				toRow >= 0; fromRow--) {
			if ((i < clearedRows.size() - 1) 
					&& (fromRow == clearedRows.get(i + 1))) {
				i++;
				continue;
			}
			for (int c = 0; c < numCols; c++) {
				setBlock(toRow, c, (fromRow < 0) ? null : getBlock(fromRow, c));
			}
			toRow--;
		}
		return clearedRows.size();
	}
}

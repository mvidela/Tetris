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

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;

/**
 * Demo for a Tetris game using gwt-g2d.
 * 
 * @author hao1300@gmail.com
 */
public class TetrisDemo extends AbstractDemo {
	private Tetris tetris;
	
	public TetrisDemo(Panel demoPanel) {
		super("Tetris", demoPanel);
	}

	@Override
	public void initialize() {
		Panel panel = new FlowPanel();
		add(panel);
		tetris = new Tetris(1, panel);
		tetris.initialize();
	}

	@Override
	public void update() {
		tetris.update();
	}
}

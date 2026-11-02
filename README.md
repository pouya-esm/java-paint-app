# Paint

A simple vector drawing program written in Java with Swing. You pick a shape (or an action) from the toolbar on the left and then click on the white canvas to draw, move, erase or resize shapes. Built as a university assignment to practice object-oriented design (abstract classes, inheritance, polymorphism, enums) and Swing event handling.

## Features

- **7 shapes:** Line, Rectangle, Circle, Ellipse, Square, Triangle and Polygon
- **Actions:** Move, Erase, Zoom In (×2) and Zoom Out (×0.5) on any shape
- **8 preset colors**, plus an **"Is Filled?"** checkbox to draw filled or outlined shapes
- Drawings are kept when the window is resized or minimized
- Clean OOP model: every shape extends the abstract `Shape` class and implements `draw`, `move`, `scale`, `erase` and `hit`

## Project structure

```
Paint/
├── src/
│   ├── paintAbstract/          # Drawing model (no GUI code except painting)
│   │   ├── Shape.java          # Abstract base class of all shapes
│   │   ├── Line.java, Circle.java, Ellipse.java, Rectangle.java,
│   │   │   Square.java, Triangle.java, Polygon.java
│   │   ├── Point.java          # 2D point helper
│   │   ├── PaintingArea.java   # Holds the shapes and paints them on the canvas
│   │   ├── ShapeTypes.java     # Enum: which shape is selected
│   │   ├── ActionType.java     # Enum: Draw, Move, Erase, ZoomIn, ZoomOut
│   │   └── MyTools.java        # Small random-number helpers
│   └── testpaint/
│       └── MyPaint.java        # Main window (JFrame): toolbar, canvas, mouse handling
├── docs/
│   ├── USAGE.md                # How to draw each shape and use the tools
│   └── ARCHITECTURE.md         # Class design and event flow
├── .gitignore
└── README.md
```

## Requirements

- Java Development Kit (JDK) 8 or newer (only `java.awt` / `javax.swing`, no external libraries)

## Build & run

From the project root:

```bash
# Compile
mkdir bin
javac -d bin src/paintAbstract/*.java src/testpaint/*.java

# Run
java -cp bin testpaint.MyPaint
```

On Windows (Command Prompt) use backslashes: `javac -d bin src\paintAbstract\*.java src\testpaint\*.java`.

You can also open the folder in Eclipse, IntelliJ IDEA or NetBeans and run `MyPaint.java` directly.

## Quick start

1. Click a shape button (for example **Circle**).
2. Click on the canvas to set the points the shape needs (a circle needs 2 clicks: center, then a point on its edge).
3. Pick a color and tick **Is Filled?** if you want a filled shape.
4. Use **Move**, **Erase**, **Zoom In** and **Zoom Out** to edit shapes you already drew.

See [docs/USAGE.md](docs/USAGE.md) for the exact clicks every shape and action needs, and [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md) for how the code is organised.

# Architecture

The program is split into a small drawing model (`paintAbstract`) and a Swing window that uses it (`testpaint`).

## Package `paintAbstract`

### `Shape` (abstract)

Common base class of every drawable object. It stores the `name`, `borderColor`, `fillColor` and `isFilled` flag, and declares the operations each shape must provide:

| Method | Purpose |
|--------|---------|
| `draw(Graphics)` | Paint the shape (outline, or filled when `isFilled` is true) |
| `move(dx, dy)` | Shift the shape by the given offset |
| `scale(factor)` | Resize around the shape's own center (`2.0` doubles, `0.5` halves) |
| `erase()` | Hook called when the shape is deleted |
| `hit(Point)` | `true` if the point is on/inside the shape (used to pick shapes with the mouse) |

### Concrete shapes

| Class | Data | `hit` test |
|-------|------|-----------|
| `Line` | two end points | the point lies (almost) on the segment |
| `Rectangle` | two opposite corners | the point is inside the bounding box |
| `Square` | top-left corner + edge length | the point is inside the square |
| `Circle` | center + radius | distance to the center ≤ radius |
| `Ellipse` | center + major/minor radius | ellipse equation ≤ 1 |
| `Triangle` | three corners | sum of the three sub-triangle areas equals the whole area |
| `Polygon` | array of vertices | ray casting (odd number of edge crossings means inside) |

### Other classes

- **`Point`** – integer x/y coordinate with helper methods (including `distance`).
- **`PaintingArea`** – owns the `ArrayList<Shape>` and the canvas component. It adds/removes/moves/scales shapes, finds the top-most shape under a point, and paints all shapes in `drawAll(Graphics)`. Any change simply asks the canvas to repaint.
- **`ShapeTypes`** and **`ActionType`** – enums describing the currently selected shape and action.
- **`MyTools`** – random helpers used to give each shape a name such as `Circle_417`.

## Package `testpaint`

### `MyPaint` (JFrame)

The main window. It contains:

- the toolbar buttons (shapes, actions, colors) and the "Is Filled?" checkbox,
- a `BorderLayout`: the fixed-width toolbar sits on the west side and the canvas takes all remaining space, so the window can be resized or maximized freely,
- the canvas, a `JPanel` whose `paintComponent` calls `PaintingArea.drawAll`, so the drawing is redrawn automatically whenever the window needs it,
- the current state: `curShape`, `curAction`, `curBorderColor`, `curFillColor`, and the points clicked so far (`p1`, `p2`, `p3`, plus `pArr2` for a polygon in progress).

## Event flow

```
Tool button click
  → set curShape / curAction
  → resetPending()  (forget half-finished clicks / polygon)

Canvas mouse click  (paintPanelMouseClicked)
  → switch (curAction)
      Draw     → collect the clicks the shape needs
                 → create the Shape (colors, isFilled from the UI)
                 → PaintingArea.addShape()
      Move     → 2 clicks → PaintingArea.moveShape(point, dx, dy)
      Erase    → 1 click  → PaintingArea.getShapeByPoint() → removeShape()
      ZoomIn   → 1 click  → PaintingArea.scaleShape(point, 2.0)
      ZoomOut  → 1 click  → PaintingArea.scaleShape(point, 0.5)
  → PaintingArea asks the canvas to repaint
      → paintComponent → PaintingArea.drawAll(Graphics)
```

## Design notes

- Polymorphism keeps the window code short: after a shape is picked, `move`, `scale`, `draw` and `hit` are all called through the `Shape` type, so adding a new shape only needs a new subclass and a toolbar button.
- The shape list is the single source of truth; the canvas never keeps pixels of its own, which is why resizing or minimizing the window does not lose the drawing.

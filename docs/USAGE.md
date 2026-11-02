# Usage Guide

Start the program:

```bash
java -cp bin testpaint.MyPaint
```

The window has a toolbar on the left (shapes, actions, colors, fill option) and a white canvas on the right. Choosing a tool resets any half-finished drawing.

## Drawing shapes

Select the shape button, then click on the canvas:

| Shape | Clicks | What they mean |
|-------|--------|----------------|
| **Line** | 2 | Start point, end point |
| **Rectangle** | 2 | Two opposite corners (any direction) |
| **Circle** | 2 | Center, then any point on the edge (the distance is the radius) |
| **Ellipse** | 3 | Center, then a point that sets one radius, then a point that sets the other |
| **Square** | 2 | Two opposite corners; the square uses the larger of the two distances as its edge |
| **Triangle** | 3 | The three corners |
| **Polygon** | 4 or more | Click every vertex. A small circle marks the first vertex; click it (after at least 3 vertices) to close the polygon |

### Colors and fill

- Click one of the 8 color squares to choose the color used for new shapes (black, yellow, blue, red, green, cyan, purple, magenta).
- Tick **Is Filled?** to draw new shapes filled; leave it unticked for outlines only.

## Editing shapes

| Action | Clicks | Description |
|--------|--------|-------------|
| **Move** | 2 | Click inside (or on) the shape, then click the place to move it to; the shape moves by the distance between the two clicks |
| **Erase** | 1 | Click on a shape to delete it |
| **Zoom In** | 1 per click | Click on a shape to make it twice as large, around its center |
| **Zoom Out** | 1 per click | Click on a shape to make it half the size, around its center |

When shapes overlap, the one drawn last (on top) is the one that is picked.

## Tips

- A line is picked only when you click very close to it; for filled shapes click anywhere inside.
- Choose another tool at any time to cancel a drawing you have started.

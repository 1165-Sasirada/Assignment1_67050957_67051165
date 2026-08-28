import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GirlPeek {
    private static final int WALK_TICKS = 12;
    private static final int BLINK_INTERVAL_TICKS = 8;

    // Color definitions
    private static final Color GIRL_SKIN = Color.decode("#FDE9E9");
    private static final Color GIRL_HAIR = Color.decode("#775C55");
    private static final Color GIRL_SHIRT = Color.decode("#D7E3F9");
    private static final Color FRAME_BACKGROUND = Color.decode("#FFFDF4");

    private static final Color EYE_COLOR = Color.decode("#463734");
    private static final Color PONY_BODY = new Color(200, 187, 235);
    private static final Color PONY_HAIR = new Color(72, 80, 134);
    private static final Color PONY_INNER_EAR = new Color(229, 135, 179);

    private static BufferedImage girlOpenFrame;
    private static BufferedImage girlBlinkFrame;
    private static BufferedImage[] ponyWalkFrames;
    private static BufferedImage ponySurprisedFrame;

    public static void draw(Graphics2D g2d, int tick) {
        ensureColoredFrames();

        int sceneTick = tick % 30;
        boolean blink = (sceneTick + 1) % BLINK_INTERVAL_TICKS == 0;
        g2d.drawImage(blink ? girlBlinkFrame : girlOpenFrame, 0, 0, null);

        if (sceneTick < WALK_TICKS) {
            int ponyX = -120 + (190 * sceneTick / (WALK_TICKS - 1));
            g2d.drawImage(ponyWalkFrames[sceneTick % 2], ponyX - 70, 0, null);
        } else {
            g2d.drawImage(ponySurprisedFrame, 0, 0, null);
        }
    }

    private static synchronized void ensureColoredFrames() {
        if (girlOpenFrame != null) {
            return;
        }

        girlOpenFrame = createGirlFrame(false);
        girlBlinkFrame = createGirlFrame(true);
        ponyWalkFrames = new BufferedImage[] {
                createPonyFrame(0),
                createPonyFrame(1)
        };
        ponySurprisedFrame = createPonyFrame(-1);
    }

    private static BufferedImage createGirlFrame(boolean blink) {
        return Tamagotchi.createBufferedImage(
                Tamagotchi.TRANSPARENT,
                (frameGraphics, buffer) -> {
                    drawGirlInFrame(frameGraphics, blink);
                    fillGirl(buffer, blink);
                });
    }

    private static BufferedImage createPonyFrame(int walkFrame) {
        return Tamagotchi.createBufferedImage(
                Tamagotchi.TRANSPARENT,
                (frameGraphics, buffer) -> {
                    if (walkFrame >= 0) {
                        drawWalkingPony(frameGraphics, 70, 365, walkFrame);
                    } else {
                        drawSurprisedPony(frameGraphics, 70, 365);
                    }
                    fillPony(buffer, walkFrame);
                });
    }

    private static void fillGirl(BufferedImage buffer, boolean blink) {
        Tamagotchi.floodFill(buffer, 420, 260, Tamagotchi.TRANSPARENT, GIRL_SKIN);
        Tamagotchi.floodFill(buffer, 320, 200, Tamagotchi.TRANSPARENT, GIRL_HAIR);
        Tamagotchi.floodFill(buffer, 520, 305, Tamagotchi.TRANSPARENT, GIRL_SHIRT);
        Tamagotchi.floodFill(buffer, 468, 304, Tamagotchi.TRANSPARENT, GIRL_SKIN);
        Tamagotchi.floodFill(buffer, 280, 200,
                Tamagotchi.TRANSPARENT, FRAME_BACKGROUND);

        if (!blink) {
            Tamagotchi.floodFill(buffer, 382, 255, Tamagotchi.TRANSPARENT, EYE_COLOR);
            Tamagotchi.floodFill(buffer, 444, 237, Tamagotchi.TRANSPARENT, EYE_COLOR);
        }
    }

    private static void fillPony(BufferedImage buffer, int walkFrame) {
        Tamagotchi.floodFill(buffer, 150, 400, Tamagotchi.TRANSPARENT, PONY_BODY);

        int[] hairSeedsX = {65, 150, 110, 190};
        int[] hairSeedsY = {440, 350, 400, 420};
        for (int i = 0; i < hairSeedsX.length; i++) {
            Tamagotchi.floodFill(buffer, hairSeedsX[i], hairSeedsY[i],
                    Tamagotchi.TRANSPARENT, PONY_HAIR);
        }

        Tamagotchi.floodFill(buffer, 112, 355, Tamagotchi.TRANSPARENT, PONY_BODY);
        Tamagotchi.floodFill(buffer, 198, 358, Tamagotchi.TRANSPARENT, PONY_INNER_EAR);
        Tamagotchi.floodFill(buffer, 190, 342, Tamagotchi.TRANSPARENT, PONY_BODY);

        int[] legSeedsX = {87, 101, 115, 145, 168};
        int[] legSeedsY = {480, 474, 480, 480, 458};
        for (int j=0; j < legSeedsX.length ; j++) {
            Tamagotchi.floodFill(buffer, legSeedsX[j], legSeedsY[j],
                    Tamagotchi.TRANSPARENT, PONY_BODY);
        }
        int frontLegY = walkFrame == 1 ? 465 : 480;
        Tamagotchi.floodFill(buffer, 165, frontLegY,
                Tamagotchi.TRANSPARENT, PONY_BODY);

        Tamagotchi.floodFill(buffer, 158, 390, Tamagotchi.TRANSPARENT, EYE_COLOR);
        Tamagotchi.floodFill(buffer, 190, 385, Tamagotchi.TRANSPARENT, EYE_COLOR);
        if (walkFrame < 0) {
            Tamagotchi.floodFill(buffer, 179, 401,
                    Tamagotchi.TRANSPARENT, EYE_COLOR);
        }
    }

    private static void drawGirlInFrame(Graphics2D g2d, boolean blink) {
        drawFrame(g2d);
        drawHeadAndHair(g2d);

        if (blink) {
            drawClosedEyes(g2d);
        } else {
            drawOpenEyes(g2d);
        }

        drawMouth(g2d);
    }

    private static void drawFrame(Graphics2D g2) {
        int lineSize = 4;
        g2.setColor(Color.BLACK);
        Tamagotchi.bresenhamLine(g2, 290, 127, 493, 127, lineSize);
        Tamagotchi.bresenhamLine(g2, 525, 157, 564, 274, lineSize);
        Tamagotchi.bresenhamLine(g2, 542, 313, 334, 313, lineSize);
        Tamagotchi.bresenhamLine(g2, 308, 295, 262, 157, lineSize);

        Tamagotchi.bezierCurve(g2, 262, 157, 257, 144, 271, 127, 290, 127, lineSize);
        Tamagotchi.bezierCurve(g2, 493, 127, 509, 127, 519, 139, 525, 157, lineSize);
        Tamagotchi.bezierCurve(g2, 564, 274, 569, 290, 566, 308, 542, 314, lineSize);
        Tamagotchi.bezierCurve(g2, 334, 314, 321, 314, 312, 307, 308, 295, lineSize);
    }

    private static void drawHeadAndHair(Graphics2D g2) {
        // วาดหน้า ผม หู คอ
        int lineSize = 3;
        g2.setColor(Color.BLACK);
        Tamagotchi.bezierCurve(g2, 327, 130, 307, 164, 301, 202, 302, 226, lineSize);
        Tamagotchi.bezierCurve(g2, 302, 226, 313, 254, 330, 273, 348, 284, lineSize);
        Tamagotchi.bezierCurve(g2, 348, 284, 353, 292, 355, 301, 354, 313, lineSize);

        Tamagotchi.bezierCurve(g2, 342, 244, 356, 218, 362, 190, 368, 162, lineSize);
        Tamagotchi.bezierCurve(g2, 368, 162, 383, 198, 402, 213, 424, 213, lineSize);
        Tamagotchi.bezierCurve(g2, 424, 213, 405, 197, 395, 184, 387, 161, lineSize);
        Tamagotchi.bezierCurve(g2, 387, 161, 424, 196, 461, 211, 479, 211, lineSize);

        // Ear
        Tamagotchi.bresenhamLine(g2, 479, 211, 489, 225, lineSize);
        Tamagotchi.bezierCurve(g2, 489, 225, 499, 207, 506, 202, 514, 202, lineSize);
        Tamagotchi.bezierCurve(g2, 514, 202, 522, 208, 524, 218, 521, 237, lineSize);
        Tamagotchi.bezierCurve(g2, 521, 237, 517, 245, 508, 246, 499, 250, lineSize);

        // Face
        Tamagotchi.bezierCurve(g2, 499, 250, 495, 261, 488, 272, 477, 279, lineSize);
        // Close the jaw contour so a face flood fill cannot leak through this gap.
        Tamagotchi.bezierCurve(g2, 477, 279, 470, 287, 462, 292, 454, 295, lineSize);
        Tamagotchi.bezierCurve(g2, 454, 295, 421, 305, 391, 304, 371, 296, lineSize);
        Tamagotchi.bezierCurve(g2, 371, 296, 365, 282, 357, 269, 342, 243, lineSize);

        // Neck
        Tamagotchi.bresenhamLine(g2, 449, 297, 457, 312, lineSize);
        Tamagotchi.bresenhamLine(g2, 477, 279, 484, 298, lineSize);

        Tamagotchi.bresenhamLine(g2, 495, 295, 487, 311, lineSize);

        // Shoulder
        Tamagotchi.bezierCurve(g2, 484, 298, 510, 293, 534, 296, 552, 312, lineSize);

    }

    private static void drawOpenEyes(Graphics2D g2) {
        int lineSize = 3;
        g2.setColor(Color.BLACK);

        Tamagotchi.midpointCircle(g2, 382, 255, 10, lineSize);
        Tamagotchi.midpointCircle(g2, 444, 237, 10, lineSize);

        Tamagotchi.bezierCurve(g2, 389, 237, 377, 233, 369, 239, 364, 252, lineSize);
        Tamagotchi.bresenhamLine(g2, 367, 245, 358, 241, lineSize);
        Tamagotchi.bresenhamLine(g2, 371, 240, 363, 237, lineSize);

        Tamagotchi.bezierCurve(g2, 429, 227, 434, 220, 444, 215, 455, 221, lineSize);
        Tamagotchi.bresenhamLine(g2, 451, 219, 458, 214, lineSize);
        Tamagotchi.bresenhamLine(g2, 448, 216, 452, 211, lineSize);

    }

    private static void drawClosedEyes(Graphics2D g2) {
        int lineSize = 3;
        g2.setColor(Color.BLACK);

        Tamagotchi.bezierCurve(g2, 370, 253, 376, 262, 384, 262, 390, 253, lineSize);
        Tamagotchi.bezierCurve(g2, 434, 235, 440, 244, 448, 244, 454, 235, lineSize);

        // ขนตา
    }

    private static void drawMouth(Graphics2D g2) {
        int lineSize = 3;
        g2.setColor(Color.BLACK);

        Tamagotchi.bezierCurve(g2, 422, 282, 433, 283, 439, 278, 441, 270, lineSize);
    }

    private static void drawWalkingPony(Graphics2D g2, int x, int y, int walkFrame) {
        // พิกัดของโพนี่ในโค้ดถูกวาดโดยอ้างอิงตำแหน่ง (70, 365)
        // จึงเลื่อน Graphics2D ทั้งก้อน เพื่อให้โพนี่เดินจากซ้ายเข้ามาได้
        g2.translate(x - 70, y - 365);

        // วาดตัวโพนี่คงที่
        int lineSize = 3;
        g2.setColor(Color.BLACK);
        Tamagotchi.bezierCurve(g2, 167, 419, 169, 429, 169, 436, 167, 446, lineSize);
        // Tamagotchi.bezierCurve(g2, 149, 462, 137, 465, 122, 465, 110, 461,
        // lineSize);
        Tamagotchi.bezierCurve(g2, 89, 453, 89, 443, 93, 427, 101, 419, lineSize);

        Tamagotchi.bezierCurve(g2, 161, 421, 180, 419, 194, 412, 204, 400, lineSize);
        Tamagotchi.bresenhamLine(g2, 161, 421, 167, 419, lineSize);
        Tamagotchi.bezierCurve(g2, 204, 400, 203, 397, 202, 394, 201, 389, lineSize);
        Tamagotchi.bezierCurve(g2, 201, 389, 201, 382, 201, 373, 197, 367, lineSize);
        Tamagotchi.bresenhamLine(g2, 197, 367, 192, 365, lineSize);
        Tamagotchi.bezierCurve(g2, 192, 365, 196, 355, 201, 341, 202, 328, lineSize);
        Tamagotchi.bezierCurve(g2, 201, 328, 191, 332, 184, 340, 179, 344, lineSize);

        Tamagotchi.bezierCurve(g2, 116, 380, 108, 368, 104, 352, 114, 337, lineSize);
        Tamagotchi.bezierCurve(g2, 114, 337, 126, 342, 133, 358, 131, 381, lineSize);

        // Hair
        Tamagotchi.bresenhamLine(g2, 192, 365, 211, 367, lineSize);
        Tamagotchi.bezierCurve(g2, 211, 367, 211, 357, 206, 347, 201, 339, lineSize);
        Tamagotchi.bezierCurve(g2, 191, 367, 187, 356, 180, 348, 174, 340, lineSize);

        Tamagotchi.bezierCurve(g2, 190, 332, 174, 327, 149, 327, 121, 342, lineSize);
        Tamagotchi.bezierCurve(g2, 109, 365, 104, 386, 103, 405, 101, 419, lineSize);
        Tamagotchi.bezierCurve(g2, 101, 419, 109, 431, 119, 438, 129, 441, lineSize);
        Tamagotchi.bresenhamLine(g2, 129, 441, 136, 422, lineSize);
        Tamagotchi.bresenhamLine(g2, 136, 422, 122, 411, lineSize);
        Tamagotchi.bresenhamLine(g2, 122, 411, 133, 415, lineSize);
        Tamagotchi.bresenhamLine(g2, 133, 415, 135, 401, lineSize);
        Tamagotchi.bezierCurve(g2, 135, 401, 125, 397, 118, 392, 117, 381, lineSize);

        Tamagotchi.bezierCurve(g2, 168, 436, 184, 434, 199, 427, 208, 417, lineSize);
        Tamagotchi.bresenhamLine(g2, 208, 417, 203, 410, lineSize);
        Tamagotchi.bezierCurve(g2, 203, 410, 209, 406, 212, 400, 215, 397, lineSize);
        Tamagotchi.bezierCurve(g2, 215, 397, 207, 389, 205, 380, 205, 366, lineSize);

        Tamagotchi.bresenhamLine(g2, 131, 383, 146, 378, lineSize);
        Tamagotchi.bresenhamLine(g2, 146, 378, 149, 370, lineSize);
        Tamagotchi.bresenhamLine(g2, 149, 370, 152, 378, lineSize);
        Tamagotchi.bresenhamLine(g2, 152, 378, 188, 366, lineSize);

        // Tail
        Tamagotchi.bezierCurve(g2, 102, 411, 88, 393, 63, 397, 49, 423, lineSize);
        Tamagotchi.bezierCurve(g2, 49, 423, 47, 435, 41, 460, 34, 458, lineSize);
        Tamagotchi.bezierCurve(g2, 34, 458, 43, 466, 61, 473, 79, 473, lineSize);
        Tamagotchi.bezierCurve(g2, 79, 473, 86, 468, 89, 460, 89, 453, lineSize);

        // Face
        Tamagotchi.midpointCircle(g2, 158, 390, 4, lineSize);
        Tamagotchi.midpointCircle(g2, 190, 385, 4, lineSize);

        Tamagotchi.bezierCurve(g2, 173, 402, 176, 405, 179, 405, 182, 400, lineSize);

        if (walkFrame == 0) {
            //ขาหน้า
            Tamagotchi.bresenhamLine(g2, 167, 446, 159, 451, lineSize);
            Tamagotchi.bresenhamLine(g2, 159, 451, 179, 481, lineSize);
            Tamagotchi.bezierCurve(g2, 179, 481, 178, 491, 166, 493, 158, 491, lineSize);
            Tamagotchi.bresenhamLine(g2, 158, 491, 145, 464, lineSize);

            Tamagotchi.bezierCurve(g2, 145, 464, 137, 465, 122, 465, 110, 461, lineSize);
            Tamagotchi.bresenhamLine(g2, 110, 461, 90, 454, lineSize);

            Tamagotchi.bezierCurve(g2, 168, 446, 173, 454, 173, 459, 171, 467, lineSize);
            Tamagotchi.bresenhamLine(g2, 154, 481, 150, 487, lineSize);
            Tamagotchi.bezierCurve(g2, 150, 487, 143, 485, 136, 478, 136, 473, lineSize);
            Tamagotchi.bresenhamLine(g2, 136, 473, 146, 465, lineSize);

            //ขาหลัง
            Tamagotchi.bezierCurve(g2, 90, 454, 81, 468, 75, 480, 76, 492, lineSize);
            Tamagotchi.bezierCurve(g2, 76, 492, 82, 495, 87, 500, 94, 495, lineSize);
            Tamagotchi.bresenhamLine(g2, 94, 495, 102, 466, lineSize);
            Tamagotchi.bresenhamLine(g2, 102, 466, 110, 462, lineSize);

            Tamagotchi.bresenhamLine(g2, 101, 472, 108, 489, lineSize);
            Tamagotchi.bezierCurve(g2, 108, 489, 116, 491, 124, 489, 129, 483, lineSize);
            Tamagotchi.bresenhamLine(g2, 129, 483, 123, 465, lineSize);
        } else {
            ; // ขาหน้า
            Tamagotchi.bresenhamLine(g2, 167, 446, 155, 446, lineSize);
            Tamagotchi.bezierCurve(g2, 155, 446, 163, 446, 171, 451, 174, 456, lineSize);
            Tamagotchi.bresenhamLine(g2, 174, 456, 179, 470, lineSize);
            Tamagotchi.bezierCurve(g2, 179, 470, 174, 478, 165, 477, 157, 475, lineSize);
            Tamagotchi.bresenhamLine(g2, 157, 475, 155, 464, lineSize);
            Tamagotchi.bezierCurve(g2, 155, 464, 150, 464, 142, 460, 138, 454, lineSize);

            Tamagotchi.bezierCurve(g2, 147, 462, 137, 465, 122, 465, 110, 461, lineSize);
            Tamagotchi.bresenhamLine(g2, 138, 454, 147, 462, lineSize);
            Tamagotchi.bresenhamLine(g2, 110, 461, 90, 454, lineSize);

            Tamagotchi.bresenhamLine(g2, 139, 464, 136, 486, lineSize);
            Tamagotchi.bezierCurve(g2, 136, 486, 142, 493, 152, 494, 159, 490, lineSize);
            Tamagotchi.bresenhamLine(g2, 159, 490, 158, 473, lineSize);

            // ขาหลัง
            Tamagotchi.bezierCurve(g2, 90, 454, 81, 468, 75, 480, 76, 485, lineSize);
            Tamagotchi.bezierCurve(g2, 76, 485, 82, 488, 87, 493, 94, 488, lineSize);
            Tamagotchi.bresenhamLine(g2, 94, 488, 102, 466, lineSize);
            Tamagotchi.bresenhamLine(g2, 102, 466, 110, 462, lineSize);

            Tamagotchi.bresenhamLine(g2, 101, 472, 108, 489, lineSize);
            Tamagotchi.bezierCurve(g2, 108, 489, 116, 491, 124, 489, 129, 483, lineSize);
            Tamagotchi.bresenhamLine(g2, 129, 483, 123, 465, lineSize);
        }

        // คืนระบบพิกัด เพื่อไม่ให้กระทบการวาดส่วนอื่นในรอบถัดไป
        g2.translate(70 - x, 365 - y);
    }

    private static void drawSurprisedPony(Graphics2D g2, int x, int y) {
        // วาดโพนี่: ตาโต ปากอ้า หูตั้ง + เส้นตกใจ
        int lineSize = 3;
        g2.setColor(Color.BLACK);

        // เส้นตกใจแผ่ออกจากด้านบนศีรษะ
        Tamagotchi.bresenhamLine(g2, 125, 324, 112, 307, lineSize);
        Tamagotchi.bresenhamLine(g2, 157, 316, 157, 294, lineSize);
        Tamagotchi.bresenhamLine(g2, 188, 322, 201, 303, lineSize);

        Tamagotchi.bezierCurve(g2, 167, 419, 169, 429, 169, 436, 167, 446, lineSize);
        // Tamagotchi.bezierCurve(g2, 149, 462, 137, 465, 122, 465, 110, 461,
        // lineSize);
        Tamagotchi.bezierCurve(g2, 89, 453, 89, 443, 93, 427, 101, 419, lineSize);

        Tamagotchi.bezierCurve(g2, 161, 421, 180, 419, 194, 412, 204, 400, lineSize);
        Tamagotchi.bresenhamLine(g2, 161, 421, 167, 419, lineSize);
        Tamagotchi.bezierCurve(g2, 204, 400, 203, 397, 202, 394, 201, 389, lineSize);
        Tamagotchi.bezierCurve(g2, 201, 389, 201, 382, 201, 373, 197, 367, lineSize);
        Tamagotchi.bresenhamLine(g2, 197, 367, 192, 365, lineSize);
        Tamagotchi.bezierCurve(g2, 192, 365, 196, 355, 201, 341, 202, 328, lineSize);
        Tamagotchi.bezierCurve(g2, 201, 328, 191, 332, 184, 340, 179, 344, lineSize);

        Tamagotchi.bezierCurve(g2, 116, 380, 108, 368, 104, 352, 114, 337, lineSize);
        Tamagotchi.bezierCurve(g2, 114, 337, 126, 342, 133, 358, 131, 381, lineSize);

        // Hair
        Tamagotchi.bresenhamLine(g2, 192, 365, 211, 367, lineSize);
        Tamagotchi.bezierCurve(g2, 211, 367, 211, 357, 206, 347, 201, 339, lineSize);
        Tamagotchi.bezierCurve(g2, 191, 367, 187, 356, 180, 348, 174, 340, lineSize);

        Tamagotchi.bezierCurve(g2, 190, 332, 174, 327, 149, 327, 121, 342, lineSize);
        Tamagotchi.bezierCurve(g2, 109, 365, 104, 386, 103, 405, 101, 419, lineSize);
        Tamagotchi.bezierCurve(g2, 101, 419, 109, 431, 119, 438, 129, 441, lineSize);
        Tamagotchi.bresenhamLine(g2, 129, 441, 136, 422, lineSize);
        Tamagotchi.bresenhamLine(g2, 136, 422, 122, 411, lineSize);
        Tamagotchi.bresenhamLine(g2, 122, 411, 133, 415, lineSize);
        Tamagotchi.bresenhamLine(g2, 133, 415, 135, 401, lineSize);
        Tamagotchi.bezierCurve(g2, 135, 401, 125, 397, 118, 392, 117, 381, lineSize);

        Tamagotchi.bezierCurve(g2, 168, 436, 184, 434, 199, 427, 208, 417, lineSize);
        Tamagotchi.bresenhamLine(g2, 208, 417, 203, 410, lineSize);
        Tamagotchi.bezierCurve(g2, 203, 410, 209, 406, 212, 400, 215, 397, lineSize);
        Tamagotchi.bezierCurve(g2, 215, 397, 207, 389, 205, 380, 205, 366, lineSize);

        Tamagotchi.bresenhamLine(g2, 131, 383, 146, 378, lineSize);
        Tamagotchi.bresenhamLine(g2, 146, 378, 149, 370, lineSize);
        Tamagotchi.bresenhamLine(g2, 149, 370, 152, 378, lineSize);
        Tamagotchi.bresenhamLine(g2, 152, 378, 188, 366, lineSize);

        // Tail
        Tamagotchi.bezierCurve(g2, 102, 411, 88, 393, 63, 397, 49, 423, lineSize);
        Tamagotchi.bezierCurve(g2, 49, 423, 47, 435, 41, 460, 34, 458, lineSize);
        Tamagotchi.bezierCurve(g2, 34, 458, 43, 466, 61, 473, 88, 474, lineSize);
        Tamagotchi.bezierCurve(g2, 88, 474, 91, 466, 91, 459, 89, 453, lineSize);

        // Face
        Tamagotchi.midpointCircle(g2, 158, 390, 4, lineSize);
        Tamagotchi.midpointCircle(g2, 190, 385, 4, lineSize);

        Tamagotchi.midpointCircle(g2, 179, 401, 3, lineSize);

        // ขาหน้า
        Tamagotchi.bresenhamLine(g2, 167, 446, 152, 460, lineSize);
        Tamagotchi.bresenhamLine(g2, 152, 460, 150, 458, lineSize);
        Tamagotchi.bresenhamLine(g2, 150, 458, 150, 490, lineSize);
        Tamagotchi.bezierCurve(g2, 150, 490, 146, 496, 138, 496, 130, 493, lineSize);
        Tamagotchi.bresenhamLine(g2, 130, 493, 129, 465, lineSize);

        Tamagotchi.bezierCurve(g2, 130, 465, 117, 462, 123, 464, 110, 461, lineSize);
        Tamagotchi.bresenhamLine(g2, 110, 461, 90, 454, lineSize);
        Tamagotchi.bezierCurve(g2, 152, 460, 160, 454, 165, 450, 167, 445, lineSize);

        Tamagotchi.bresenhamLine(g2, 165, 450, 169, 486, lineSize);
        Tamagotchi.bezierCurve(g2, 169, 486, 165, 492, 158, 493, 151, 489, lineSize);

        // ขาหลัง
        Tamagotchi.bezierCurve(g2, 90, 454, 87, 472, 85, 484, 87, 495, lineSize);
        Tamagotchi.bezierCurve(g2, 87, 495, 94, 498, 99, 498, 106, 495, lineSize);
        Tamagotchi.bezierCurve(g2, 108, 495, 107, 484, 106, 471, 109, 460, lineSize);

        Tamagotchi.bezierCurve(g2, 120, 463, 121, 476, 123, 484, 123, 490, lineSize);
        Tamagotchi.bezierCurve(g2, 123, 490, 119, 495, 115, 493, 108, 493, lineSize);
    }

}

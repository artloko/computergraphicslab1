package controller;

public class ColorConverter {

    /**
     * RGB -> HSV.
     * Adds (hue + 360) % 360 for represent hue in the range [0..359].
     * @param red Red coefficient. Values in the range [0..255].
     * @param green Green coefficient. Values in the range [0..255].
     * @param blue Blue coefficient. Values in the range [0..255].
     * @return HSV color space.
     */
    public static float[] RGBtoHSV(int red, int green, int blue){
        float[] hsv = new float[3];
        float r = red / 255f;
        float g = green / 255f;
        float b = blue / 255f;

        float max = Math.max(r, Math.max(g, b));
        float min = Math.min(r, Math.min(g, b));
        float delta = max - min;

        // Hue
        if (max == min){
            hsv[0] = 0;
        }
        else if (max == r){
            hsv[0] = ((g - b) / delta) * 60f;
        }
        else if (max == g){
            hsv[0] = ((b - r) / delta + 2f) * 60f;
        }
        else if (max == b){
            hsv[0] = ((r - g) / delta + 4f) * 60f;
        }

        if (hsv[0] < 0)
            hsv[0] += 360f;

        // Saturation
        if (delta == 0)
            hsv[1] = 0;
        else
            hsv[1] = delta / max;

        //Value
        hsv[2] = max;

        //System.out.println(Arrays.toString(hsv));
        return hsv;
    }

    /**
     * HSV -> RGB.
     * @param hue Hue.
     * @param saturation Saturation. In the range[0..1].
     * @param value Value. In the range[0..1].
     * @return RGB color space. In the range[0..255].
     */
    public static int[] HSVtoRGB(float hue, float saturation, float value){
        int[] rgb = new int[3];

        float hi = (float)Math.floor(hue / 60.0) % 6;
        float f =  (float)((hue / 60.0) - Math.floor(hue / 60.0));
        float p = (float)(value * (1.0 - saturation));
        float q = (float)(value * (1.0 - (f * saturation)));
        float t = (float)(value * (1.0 - ((1.0 - f) * saturation)));

        if (hi == 0){
            rgb[0] = (int)(value * 255);
            rgb[1] = (int)(t * 255);
            rgb[2] = (int)(p * 255);
        }
        else if (hi == 1){
            rgb[0] = (int)(q * 255);
            rgb[1] = (int)(value * 255);
            rgb[2] = (int)(p * 255);
        }
        else if (hi == 2){
            rgb[0] = (int)(p * 255);
            rgb[1] = (int)(value * 255);
            rgb[2] = (int)(t * 255);
        }
        else if (hi == 3){
            rgb[0] = (int)(p * 255);
            rgb[1] = (int)(value * 255);
            rgb[2] = (int)(q * 255);
        }
        else if (hi == 4){
            rgb[0] = (int)(t * 255);
            rgb[1] = (int)(value * 255);
            rgb[2] = (int)(p * 255);
        }
        else if (hi == 5){
            rgb[0] = (int)(value * 255);
            rgb[1] = (int)(p * 255);
            rgb[2] = (int)(q * 255);
        }

        return rgb;
    }

    /**
     * RGB -> XYZ
     * @param red Red coefficient. Values in the range [0..255].
     * @param green Green coefficient. Values in the range [0..255].
     * @param blue Blue coefficient. Values in the range [0..255].
     * @return XYZ color space.
     */
    public static float[] RGBtoXYZ(int red, int green, int blue){
        float[] xyz = new float[3];

        float r = red / 255f;
        float g = green / 255f;
        float b = blue / 255f;

        //R
        if ( r > 0.04045)
            r = (float)Math.pow(( ( r + 0.055f ) / 1.055f ), 2.4f);
        else
            r /= 12.92f;

        //G
        if ( g > 0.04045)
            g = (float)Math.pow(( ( g + 0.055f ) / 1.055f ), 2.4f);
        else
            g /= 12.92f;

        //B
        if ( b > 0.04045)
            b = (float)Math.pow(( ( b + 0.055f ) / 1.055f ), 2.4f);
        else
            b /= 12.92f;

        r *= 100;
        g *= 100;
        b *= 100;

        float x = 0.412453f * r + 0.35758f * g + 0.180423f * b;
        float y = 0.212671f * r + 0.71516f * g + 0.072169f * b;
        float z = 0.019334f * r + 0.119193f * g + 0.950227f * b;

        xyz[0] = x;
        xyz[1] = y;
        xyz[2] = z;

        return xyz;
    }

    /**
     * XYZ -> RGB
     * @param x X coefficient.
     * @param y Y coefficient.
     * @param z Z coefficient.
     * @return RGB color space.
     */
    public static int[] XYZtoRGB(float x, float y, float z){
        int[] rgb = new int[3];

        x /= 100;
        y /= 100;
        z /= 100;

        float r = 3.240479f * x - 1.53715f * y - 0.498535f * z;
        float g = -0.969256f * x + 1.875991f * y + 0.041556f * z;
        float b = 0.055648f * x - 0.204043f * y + 1.057311f * z;

        if ( r > 0.0031308 )
            r = 1.055f * ( (float)Math.pow(r, 0.4166f) ) - 0.055f;
        else
            r = 12.92f * r;

        if ( g > 0.0031308 )
            g = 1.055f * ( (float)Math.pow(g, 0.4166f) ) - 0.055f;
        else
            g = 12.92f * g;

        if ( b > 0.0031308 )
            b = 1.055f * ( (float)Math.pow(b, 0.4166f) ) - 0.055f;
        else
            b = 12.92f * b;

        rgb[0] = (int)(r * 255);
        rgb[1] = (int)(g * 255);
        rgb[2] = (int)(b * 255);

        return rgb;
    }
}

#version 330

in vec2 uv;

uniform sampler2D texture;

void main() {
    vec4 color = texture2D(texture,uv);

    gl_FragColor = color;
}
/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        'primaryFont': '#0f486b',
        'secondaryColor': '#147cd1',
        'secondaryBg': '#edf4fa'
      },
      fontFamily: {
        'primaryFont': ['Figtree', 'sans-serif'],
      },
    },
  },
  plugins: [
    require('daisyui'),
  ],
}


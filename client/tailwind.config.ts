import type { Config } from "tailwindcss";
import { nextui } from "@nextui-org/react";

const config: Config = {
  content: [
    "./pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./components/**/*.{js,ts,jsx,tsx,mdx}",
    "./app/**/*.{js,ts,jsx,tsx,mdx}",
    "./node_modules/@nextui-org/theme/dist/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      backgroundImage: {
        "gradient-radial": "radial-gradient(var(--tw-gradient-stops))",
        "gradient-conic":
          "conic-gradient(from 180deg at 50% 50%, var(--tw-gradient-stops))",
      },
      colors: {
        accent: "#FF7961",
        accent2: "#F35C7E",
        grading1: "#e20c0c",
        grading2: "#f2831f",
        grading3: "#fbdd10",
        grading4: "#85ef08",
        grading5: "#1bff00",
      },
    },
  },
  darkMode: "class",
  plugins: [nextui()],
};
export default config;

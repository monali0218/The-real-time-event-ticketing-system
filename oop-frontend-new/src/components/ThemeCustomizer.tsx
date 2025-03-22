import React from "react";
import { useTheme } from "../contexts/ThemeContext";
import { Settings } from "lucide-react";

const colorOptions = {
  background: [
    { name: "Light Gray", value: "bg-gray-100" },
    { name: "Light Blue", value: "bg-blue-50" },
    { name: "Light Green", value: "bg-green-50" },
    { name: "Light Purple", value: "bg-purple-50" },
  ],
  primary: [
    { name: "Blue", value: "bg-blue-600" },
    { name: "Green", value: "bg-green-600" },
    { name: "Purple", value: "bg-purple-600" },
    { name: "Red", value: "bg-red-600" },
  ],
};

export function ThemeCustomizer() {
  const { colors, updateColors } = useTheme();

  return (
    <div className="fixed top-4 right-4 z-50">
      <div className="relative group">
        <button className="p-2 rounded-full bg-white shadow-md hover:shadow-lg transition-shadow">
          <Settings className="w-5 h-5 text-gray-600" />
        </button>

        <div className="absolute right-0 mt-2 w-64 p-4 bg-white rounded-lg shadow-xl opacity-0 invisible group-hover:opacity-100 group-hover:visible transition-all">
          <h3 className="text-sm font-semibold mb-3">Customize Theme</h3>

          <div className="space-y-4">
            <div>
              <label className="block text-sm font-medium mb-2">
                Background Color
              </label>
              <div className="grid grid-cols-2 gap-2">
                {colorOptions.background.map((color) => (
                  <button
                    key={color.value}
                    onClick={() => updateColors({ background: color.value })}
                    className={`p-2 rounded ${color.value} border ${
                      colors.background === color.value
                        ? "border-blue-500"
                        : "border-gray-200"
                    }`}
                  >
                    {color.name}
                  </button>
                ))}
              </div>
            </div>

            <div>
              <label className="block text-sm font-medium mb-2">
                Primary Color
              </label>
              <div className="grid grid-cols-2 gap-2">
                {colorOptions.primary.map((color) => (
                  <button
                    key={color.value}
                    onClick={() => updateColors({ primary: color.value })}
                    className={`p-2 rounded text-white ${color.value} ${
                      colors.primary === color.value
                        ? "ring-2 ring-offset-2"
                        : ""
                    }`}
                  >
                    {color.name}
                  </button>
                ))}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

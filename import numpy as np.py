import numpy as np
import matplotlib.pyplot as plt
from PIL import Image
from scipy.signal import convolve2d, windows

# ==========================================
# PARTIE 1 : Lissage Gaussien (Gaussian Blur)
# ==========================================

def gaussian_kernel(size=10, sigma=3):
    """Crée un noyau Gaussien 2D."""
    # Correction : 'gau__ian' devient 'gaussian'
    g = windows.gaussian(size, std=sigma)
    G = np.outer(g, g)
    return G / G.sum()

# Load and convert image to grayscale array
# (J'ai mis '3.jpg' pour correspondre à ton image d'hélicoptère, modifie si besoin)
gray_img = np.array(Image.open('3.jpg').convert('L'))

# Create Gaussian kernel and convolve
# Correction : Il manquait le nom de la variable 'G'
G = gaussian_kernel(10, 3)
gaussian_img = convolve2d(gray_img, G, mode='same')

# --- Affichage du Lissage ---
plt.figure()
plt.title('Smoothed Image')
plt.axis('off')
# Correction : 'imsh_w' devient 'imshow'
plt.imshow(gaussian_img, cmap='gray')
plt.show()

# --- Affichage de la différence (Détails supprimés par le lissage) ---
plt.figure()
# Correction : 'cm_p' devient 'cmap'
plt.imshow(gray_img - gaussian_img, cmap='gray')
plt.title('Original Image - Smoothed Image')
plt.axis('off')
plt.show()


# ==========================================
# PARTIE 2 : Noyau Gaussien avec Meshgrid
# ==========================================

def gaussian_kernel_meshgrid(size=21, sigma=5):
    """Création alternative d'un noyau Gaussien utilisant meshgrid."""
    ax = np.linspace(-(size // 2), size // 2, size)
    # Correction : 'mes_grid' devient 'meshgrid'
    xx, yy = np.meshgrid(ax, ax)
    G_mesh = np.exp(-(xx**2 + yy**2) / (2 * sigma**2))
    return G_mesh / G_mesh.sum()

G_mesh = gaussian_kernel_meshgrid(size=21, sigma=5)

plt.figure()
plt.imshow(G_mesh, cmap='gray')
plt.colorbar()
plt.title('2D Gaussian Kernel')
plt.axis('off')
plt.show()


# ==========================================
# PARTIE 3 : Détection de contours (Sobel)
# ==========================================

# Remplissage des matrices de Sobel d'après les slides de ton cours.
# Ces matrices doivent sommer à zéro.

# sobel_y détecte les contours horizontaux
sobel_y = np.array([[ 1,  2,  1],
                    [ 0,  0,  0],
                    [-1, -2, -1]])

# sobel_x détecte les contours verticaux
sobel_x = np.array([[ 1,  0, -1],
                    [ 2,  0, -2],
                    [ 1,  0, -1]])

# Application des filtres avec convolution 2D
fy = convolve2d(gray_img, sobel_y, mode='same')
fx = convolve2d(gray_img, sobel_x, mode='same')

# --- Affichage des contours ---
plt.figure()
plt.imshow(fy, cmap='gray')
plt.title('Sobel Y (Horizontal Edges)')
plt.axis('off')

plt.figure()
plt.imshow(fx, cmap='gray')
plt.title('Sobel X (Vertical Edges)')
plt.axis('off')

plt.show()
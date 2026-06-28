from PIL import Image
import matplotlib.pyplot as plt
import numpy as np

def feature_positions(img):
    """
    Demande à l'utilisateur de cliquer sur deux points de repère.
    Pour l'hélicoptère, clique sur la QUEUE puis sur le NEZ (ou inversement).
    """
    # One-at-a-time clicks are more robust. Block for each.[cite: 5]
    plt.figure()
    plt.imshow(img)
    plt.axis('off')
    
    # Step 1: Premier point
    plt.title("Click on the FIRST feature (e.g., tail)")
    x1, y1 = plt.ginput(1, timeout=-1)[0]
    plt.plot(x1, y1, 'gx')
    plt.draw()
    
    # Step 2: Deuxième point
    plt.title("Click on the SECOND feature (e.g., nose)")
    x2, y2 = plt.ginput(1, timeout=-1)[0]
    plt.plot(x2, y2, 'gx')
    plt.draw()
    plt.close()
    
    return (x1, y1), (x2, y2)  # return left, right tuple[cite: 5]


def extract_object(img):
    """
    Extrait, pivote et redimensionne l'objet à 200x100 pixels sans distorsion.
    """
    # Convert to grayscale[cite: 5]
    gray_img = img.convert('L')
    
    # Get feature positions[cite: 5]
    c1, c2 = feature_positions(gray_img)
    
    # Calculate angle[cite: 5]
    angle = np.degrees(np.arctan2(c2[1] - c1[1], c2[0] - c1[0]))
    print(f"Angle calculé = {angle:.2f} degrés")
    
    # Rotate image[cite: 5]
    I2_img = gray_img.rotate(angle, expand=False)
    I2 = np.array(I2_img)

    # Center coordinates[cite: 5]
    h, w = I2.shape
    
    # --- CORRECTION DE LA MATRICE DE ROTATION ---
    # Comme l'axe Y pointe vers le bas, les signes des Sinus sont inversés 
    # par rapport à une matrice trigonométrique classique.
    R = np.array([[np.cos(np.radians(angle)),  np.sin(np.radians(angle))],
                  [-np.sin(np.radians(angle)), np.cos(np.radians(angle))]])
    
    new_c1 = (R @ (np.array(c1).reshape(2, 1) - np.array([[w/2], [h/2]]))) + np.array([[w/2], [h/2]])
    new_c2 = (R @ (np.array(c2).reshape(2, 1) - np.array([[w/2], [h/2]]))) + np.array([[w/2], [h/2]])

    plt.imshow(img, cmap='gray')
    
    # Show features on rotated image
    plt.figure()
    ax = plt.gca()
    ax.imshow(I2, cmap='gray')
    ax.plot(new_c1[0], new_c1[1], 'go')
    ax.plot(new_c2[0], new_c2[1], 'go')
    plt.title("Rotated image with feature positions")
    plt.show(block=False)
    plt.figure()
    
    # size of object (Calcul de la distance euclidienne avec ** 2 pour le carré)
    scale = np.sqrt(np.sum((new_c1 - new_c2) ** 2))

    # Crop and resize
    x, y = new_c1.flatten()
    
    # Définition de la bounding box. On utilise une marge proportionnelle à 'scale'
    # left, upper, right, lower
    crop_box = (int(x - scale * 0.2), int(y - scale * 0.5), int(x + scale * 1.2), int(y + scale * 0.5))
    cropped = I2_img.crop(crop_box)
    
    # Redimensionnement requis par ton examen : 200 pixels de large, 100 pixels de haut
    cropped = cropped.resize((200, 100))

    # Show crop
    plt.imshow(cropped, cmap='gray')
    plt.axis('off')
    plt.show(block=False)

    return cropped

if __name__ == "__main__":
    # Remplace '3.jpg' par l'image de ton choix (le chien ou l'hélicoptère)
    img = Image.open('2.jpg') 
    
    # J'ai retiré le rotate(45) codé en dur qui était dans ton script de test
    registered_image = extract_object(img)
    
    plt.figure()
    plt.imshow(registered_image, cmap='gray')
    plt.title("Registered Image (200x100)")
    plt.show()
def efficacite(text: str) -> str:
    if not text:
        return ""
    
    result = []
    count = 1
    length = len(text)
    
    for i in range(1, length):
        if text[i] == text[i - 1]:
            count += 1
        else:
            result.append(f"{count}{text[i - 1]}")
            count = 1
    
    result.append(f"{count}{text[-1]}")
    
    return ''.join(result)

# Exemple d'utilisation
if __name__ == "__main__":
    test_string = "WWWWWWWWWBWWWWWWWWBBBWWWBWWWWWWW"
    compressed = efficacite(test_string)
    print(compressed)  # devrait imprimer "9W1B8W3B3W1B7W"

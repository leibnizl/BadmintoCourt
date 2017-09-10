public enum Court {
    A, B, C, D;

    public static Court getCourt(String name) {
        switch (name) {
            case "A":
                return A;
            case "B":
                return B;
            case "C":
                return C;
            case "D":
                return D;
            default:
                return null;
        }
    }
}

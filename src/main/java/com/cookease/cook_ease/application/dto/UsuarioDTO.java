package com.cookease.cook_ease.application.dto;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Integer idUsuario;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 100, message = "El apellido no puede exceder 100 caracteres")
    private String apellido;

    @NotBlank(message = "El género es obligatorio")
    @Size(max = 20, message = "El género no puede exceder 20 caracteres")
    private String gender; // "Male", "Female", etc.

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 0, message = "La edad no puede ser negativa")
    private Integer age;

    @NotNull(message = "La altura es obligatoria")
    @DecimalMin(value = "0.00", inclusive = false, message = "La altura debe ser positiva")
    @Digits(integer = 4, fraction = 2, message = "La altura debe tener hasta 4 dígitos enteros y 2 decimales")
    private BigDecimal height; // En metros, ej. 1.10

    @NotNull(message = "El peso es obligatorio")
    @Min(value = 0, message = "El peso no puede ser negativo")
    private Integer weight; // En kilogramos

    @Size(max = 10, message = "La historia familiar no puede exceder 10 caracteres")
    private String family_history_with_overweight; // "yes", "no"

    @Size(max = 10, message = "FAVC no puede exceder 10 caracteres")
    private String FAVC; // "yes", "no"

    private Integer FCVC;

    private Integer NCP;

    @Size(max = 20, message = "CAEC no puede exceder 20 caracteres")
    private String CAEC; // "Sometimes", etc.

    @Size(max = 10, message = "SMOKE no puede exceder 10 caracteres")
    private String SMOKE; // "yes", "no"

    @DecimalMin(value = "0.0", inclusive = true, message = "CH2O debe ser positivo")
    @Digits(integer = 3, fraction = 1, message = "CH2O debe tener hasta 3 dígitos enteros y 1 decimal")
    private BigDecimal CH2O;

    @Size(max = 10, message = "SCC no puede exceder 10 caracteres")
    private String SCC; // "yes", "no"

    private Integer FAF;

    private Integer TUE;

    @Size(max = 10, message = "CALC no puede exceder 10 caracteres")
    private String CALC; // "yes", "no"

    @Size(max = 20, message = "MTRANS no puede exceder 20 caracteres")
    private String MTRANS; // "Walking", etc.

    private Set<String> etiquetas; // Nombres de Etiquetas asignadas (Opcional)
}
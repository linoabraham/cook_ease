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
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 100, message = "El apellido no puede exceder 100 caracteres")
    private String lastName;

    @NotBlank(message = "El género es obligatorio")
    @Size(max = 100, message = "El género no puede exceder 100 caracteres")
    private String gender;

    @NotBlank(message = "La fecha de nacimiento es obligatoria")
    @Size(max = 100, message = "La fecha de nacimiento no puede exceder 100 caracteres")
    private String birthDate;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    @Size(max = 100, message = "El email no puede exceder 100 caracteres")
    private String email;

    @DecimalMin(value = "0.00", inclusive = false, message = "La altura debe ser positiva")
    @Digits(integer = 3, fraction = 2, message = "La altura debe tener hasta 3 dígitos enteros y 2 decimales")
    private BigDecimal height;

    @DecimalMin(value = "0.00", inclusive = false, message = "El peso debe ser positivo")
    @Digits(integer = 3, fraction = 2, message = "El peso debe tener hasta 3 dígitos enteros y 2 decimales")
    private BigDecimal weight;

    @NotBlank(message = "El nivel de actividad es obligatorio")
    @Size(max = 80, message = "El nivel de actividad no puede exceder 80 caracteres")
    private String activityLevel;

    private String fitnessGoals;

    private String allergies;

    @DecimalMin(value = "0.0", inclusive = true, message = "Las horas de sueño deben ser positivas")
    @Digits(integer = 1, fraction = 1, message = "Las horas de sueño deben tener hasta 1 dígito entero y 1 decimal")
    private BigDecimal sleepHours;

    private Boolean fixedMealSchedule;

    @DecimalMin(value = "0.0", inclusive = true, message = "El tiempo de preparación de comidas debe ser positivo")
    @Digits(integer = 1, fraction = 1, message = "El tiempo de preparación de comidas debe tener hasta 1 dígito entero y 1 decimal")
    private BigDecimal mealPreparationTime;

    @Min(value = 0, message = "La frecuencia de comidas fuera de casa no puede ser negativa")
    private Integer outOfHomeMealsFrequency;

    @Size(max = 50, message = "El acceso a tiendas saludables no puede exceder 50 caracteres")
    private String accessToHealthyStores;

    @Size(max = 100, message = "La proteína preferida no puede exceder 100 caracteres")
    private String preferredProtein;

    @Size(max = 50, message = "La frecuencia de consumo de frutas no puede exceder 50 caracteres")
    private String fruitConsumptionFrequency;

    private Set<Integer> etiquetas; // IDs de Etiquetas asignadas
}
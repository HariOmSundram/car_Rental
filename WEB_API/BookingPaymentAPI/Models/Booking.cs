using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace BookingPaymentAPI.Models
{
    [Table("booking")] // Ensure this matches your actual table name in the database
    public class Booking
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        [Column("booking_id")] 
        public int BookingId { get; set; }

        [Required]
        [Column("customer_id")] 
        public int CustomerId { get; set; }

        [Required]
        [Column("agency_id")] // Ensure this column is correctly mapped
        public int AgencyId { get; set; }

        [Required]
        [Column("car_id")] 
        public int CarId { get; set; }

        [Column("booking_date")]
        public DateOnly BookingDate { get; set; } 

        [Required]
        [Column("rental_duration")] 
        public int RentalDuration { get; set; }

        [Required]
        [Column("journey_date")] 
        public DateOnly JourneyDate { get; set; }

        [Required]  // Ensure this field is included as it exists in DB
        [Column("status_id")] 
        public int StatusId { get; set; } 

        [Column("token_amount")] 
        public decimal? TokenAmount { get; set; }
    }
}
